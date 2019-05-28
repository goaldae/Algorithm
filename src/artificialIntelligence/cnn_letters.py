import tensorflow as tf

filename_queue = tf.train.string_input_producer(
    ["C:/Users/김윤욱/Desktop/workspace/Algorithm/src/artificialIntelligence/letters_dataset/emnist-letters-train.csv"],
            shuffle=False, name='filename_queue')
reader = tf.TextLineReader()
key, value = reader.read(filename_queue)

record_defaults = [[0.]]*785
data = tf.decode_csv(value, record_defaults=record_defaults)

# hyper parameters
learning_rate = 0.0001
training_epochs = 100
batch_size = 100

# dropout (keep_prob) rate  0.7~0.5 on training, but should be 1 for testing
keep_prob = tf.placeholder(tf.float32)

# input place holders
nb_classes = 26
depth = nb_classes

X = tf.placeholder(tf.float32, [None, 784])
X_img = tf.reshape(X, [-1, 28, 28, 1])   # img 28x28x1 (black/white)
Y = tf.placeholder(tf.float32, [None, nb_classes])

train_x_batch, train_y_batch = tf.train.batch([data[1:], data[0]], batch_size=100)
train_y_batch = tf.one_hot(tf.dtypes.cast(train_y_batch, tf.int32), 26)
train_y_batch = tf.dtypes.cast(train_y_batch, tf.float32)

X = tf.placeholder(tf.float32, [None, 784])
X_img = tf.reshape(X, [-1, 28, 28, 1])   # img 28x28x1 (black/white)
Y = tf.placeholder(tf.int32, [None, nb_classes])

# L1 ImgIn shape=(?, 28, 28, 1)
W1 = tf.Variable(tf.random_normal([3, 3, 1, 32], stddev=0.01))
L1 = tf.nn.conv2d(X_img, W1, strides=[1, 1, 1, 1], padding='SAME')
L1 = tf.nn.relu(L1)
L1 = tf.nn.max_pool(L1, ksize=[1, 2, 2, 1],
                    strides=[1, 2, 2, 1], padding='SAME')
L1 = tf.nn.dropout(L1, keep_prob=keep_prob)

# L2 ImgIn shape=(?, 14, 14, 32)
W2 = tf.Variable(tf.random_normal([3, 3, 32, 64], stddev=0.01))
L2 = tf.nn.conv2d(L1, W2, strides=[1, 1, 1, 1], padding='SAME')
L2 = tf.nn.relu(L2)
L2 = tf.nn.max_pool(L2, ksize=[1, 2, 2, 1],
                    strides=[1, 2, 2, 1], padding='SAME')
L2 = tf.nn.dropout(L2, keep_prob=keep_prob)

# L3 ImgIn shape=(?, 7, 7, 64)
W3 = tf.Variable(tf.random_normal([3, 3, 64, 128], stddev=0.01))
L3 = tf.nn.conv2d(L2, W3, strides=[1, 1, 1, 1], padding='SAME')
L3 = tf.nn.relu(L3)
L3 = tf.nn.max_pool(L3, ksize=[1, 2, 2, 1], strides=[
                    1, 2, 2, 1], padding='SAME')
L3 = tf.nn.dropout(L3, keep_prob=keep_prob)
L3_flat = tf.reshape(L3, [-1, 128 * 4 * 4])

# L4 FC 4x4x128 inputs -> 625 outputs
W4 = tf.get_variable("W4", shape=[128 * 4 * 4, 625],
                     initializer=tf.contrib.layers.xavier_initializer())
b4 = tf.Variable(tf.random_normal([625]))
L4 = tf.nn.relu(tf.matmul(L3_flat, W4) + b4)
L4 = tf.nn.dropout(L4, keep_prob=keep_prob)

# L5 Final FC 625 inputs -> 26 outputs
W5 = tf.get_variable("W5", shape=[625, nb_classes],
                     initializer=tf.contrib.layers.xavier_initializer())
b5 = tf.Variable(tf.random_normal([nb_classes]))
logits = tf.matmul(L4, W5) + b5

cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits_v2(logits=logits, labels=Y))
optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate).minimize(cost)

# initialize
sess = tf.Session()
sess.run(tf.global_variables_initializer())
coord = tf.train.Coordinator()
threads = tf.train.start_queue_runners(sess=sess, coord=coord)

prediction = tf.argmax(logits, 1)
acct_mat = tf.equal(prediction, tf.argmax(Y, 1))
acct_res = tf.reduce_mean(tf.cast(acct_mat, tf.float32))

# train my model
print('Learning started. It takes sometime.')
for epoch in range(training_epochs):
    avg_cost = 0.0
    cost_test = 0.0
    acc_test = 0.0
    avg_acc = 0.0

    # total_batch = int(88800 / batch_size)

    for i in range(100):
        batch_xs, batch_ys = sess.run([train_x_batch, train_y_batch])
        # print(batch_xs.shape, batch_ys.shape)  # print shape of each variable
        # print(batch_xs, '\n', batch_ys)
        feed_dict = {X: batch_xs, Y: batch_ys, keep_prob: 0.7}
        c, acc, _ = sess.run([cost, acct_res, optimizer], feed_dict=feed_dict)
        cost_test += c
        acc_test += acc

    avg_cost = cost_test / 100
    avg_acc = acc_test / 100

    print('Epoch:', '%04d' % (epoch + 1), '\tcost =', '{:.9f}'.format(avg_cost),
                '\tacc =', '{:.2%}'.format(avg_acc))


# 쓰레드 멈춤
coord.request_stop()
# 쓰레드가 끝나기 전에 프로그램이 종료되는 것을 막기 위해 기다림
coord.join(threads)