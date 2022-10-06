{\rtf1\ansi\ansicpg949\cocoartf2639
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fmodern\fcharset0 Courier;\f1\fnil\fcharset129 AppleSDGothicNeo-Regular;\f2\fnil\fcharset0 Menlo-Regular;
}
{\colortbl;\red255\green255\blue255;\red191\green100\blue38;\red32\green32\blue32;\red153\green168\blue186;
\red160\green0\blue163;\red128\green63\blue122;\red88\green118\blue71;\red117\green114\blue185;\red86\green132\blue173;
\red254\green187\blue91;\red109\green109\blue109;}
{\*\expandedcolortbl;;\csgenericrgb\c74902\c39216\c14902;\csgenericrgb\c12549\c12549\c12549;\csgenericrgb\c60000\c65882\c72941;
\csgenericrgb\c62745\c0\c63922;\csgenericrgb\c50196\c24706\c47843;\csgenericrgb\c34510\c46275\c27843;\csgenericrgb\c45882\c44706\c72549;\csgenericrgb\c33725\c51765\c67843;
\csgenericrgb\c99608\c73333\c35686;\csgenericrgb\c42745\c42745\c42745;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\pardirnatural\partightenfactor0

\f0\fs26 \cf2 \cb3 import \cf4 heapq\
\
\cf2 class \cf4 info:\
    \cf2 def \cf5 __init__\cf4 (\cf6 self\cf2 , \cf4 love\cf2 , \cf4 emp\cf2 , \cf4 r\cf2 , \cf4 c):\
        \cf6 self\cf4 .love = love\
        \cf6 self\cf4 .emp = emp\
        \cf6 self\cf4 .r = r\
        \cf6 self\cf4 .c = c\
\
    \cf2 def \cf5 __lt__\cf4 (\cf6 self\cf2 , \cf4 other):\
        \cf2 if \cf6 self\cf4 .love == other.love:\
            \cf2 if \cf6 self\cf4 .emp == other.emp:\
                \cf2 if \cf6 self\cf4 .r == other.r:\
                    \cf2 return \cf6 self\cf4 .c < other.c\
                \cf2 return \cf6 self\cf4 .r < other.r\
            \cf2 return \cf6 self\cf4 .emp > other.emp\
        \cf2 return \cf6 self\cf4 .love > other.love\
\
    \cf2 def \cf5 __str__\cf4 (\cf6 self\cf4 ):\
        \cf2 return \cf7 "\{\} \{\} \{\} \{\}"\cf4 .format(\cf6 self\cf4 .love\cf2 , \cf6 self\cf4 .emp\cf2 , \cf6 self\cf4 .r\cf2 , \cf6 self\cf4 .c)\
\
\
N = \cf8 int\cf4 (\cf8 input\cf4 ())\
field = [[\cf9 0\cf4 ] * (N + \cf9 1\cf4 ) \cf2 for \cf4 _ \cf2 in \cf8 range\cf4 (N + \cf9 1\cf4 )]\
\
pq = []\
data = []\
newData = [\cf9 0 \cf2 for \cf4 _ \cf2 in \cf8 range\cf4 (N * N + \cf9 1\cf4 )]\
dx = (-\cf9 1\cf2 , \cf9 0\cf2 , \cf9 1\cf2 , \cf9 0\cf4 )\
dy = (\cf9 0\cf2 , \cf4 -\cf9 1\cf2 , \cf9 0\cf2 , \cf9 1\cf4 )\
\
\cf2 for \cf4 i \cf2 in \cf8 range\cf4 (N * N):\
    data.append(\cf8 list\cf4 (\cf8 map\cf4 (\cf8 int\cf2 , \cf8 input\cf4 ().split())))\
\
\cf2 for \cf4 item \cf2 in \cf4 data:\
    newData[item[\cf9 0\cf4 ]] = item[\cf9 1\cf4 :]\
\
\
\cf2 def \cf10 pick\cf4 (i\cf2 , \cf4 j\cf2 , \cf4 item\cf2 , \cf4 field):\
    love = \cf9 0\
    \cf4 emp = \cf9 0\
\
    \cf2 for \cf4 k \cf2 in \cf8 range\cf4 (\cf9 4\cf4 ):\
        nx = j + dx[k]\
        ny = i + dy[k]\
        \cf2 if \cf4 nx < \cf9 1 \cf2 or \cf4 nx > N \cf2 or \cf4 ny > N \cf2 or \cf4 ny < \cf9 1\cf4 :\
            \cf2 continue\
\
        if \cf4 field[ny][nx] == \cf9 0\cf4 :\
            emp += \cf9 1\
\
        \cf2 for \cf4 l \cf2 in \cf8 range\cf4 (\cf9 1\cf2 , \cf9 5\cf4 ):\
            \cf2 if \cf4 field[ny][nx] == item[l]:\
                love += \cf9 1\
\
    \cf2 return \cf4 info(love\cf2 , \cf4 emp\cf2 , \cf4 i\cf2 , \cf4 j)\
\
\
\cf2 for \cf4 item \cf2 in \cf4 data:\
    pq.clear()\
    \cf2 for \cf4 i \cf2 in \cf8 range\cf4 (\cf9 1\cf2 , \cf4 N + \cf9 1\cf4 ):\
        \cf2 for \cf4 j \cf2 in \cf8 range\cf4 (\cf9 1\cf2 , \cf4 N + \cf9 1\cf4 ):\
            \cf2 if \cf4 field[i][j] != \cf9 0\cf4 :\
                \cf2 continue  \cf11 # 
\f1 \'b8\'ca\'bf\'a1
\f2  
\f1 \'c0\'cc\'b9\'cc
\f2  
\f1 \'b0\'aa\'c0\'cc
\f2  
\f1 \'b5\'e9\'be\'ee\'c0\'d6\'c0\'b8\'b8\'e9
\f2  
\f1 \'bb\'fd\'b7\'ab
\f2 \
            
\f0 \cf4 heapq.heappush(pq\cf2 , \cf4 pick(i\cf2 , \cf4 j\cf2 , \cf4 item\cf2 , \cf4 field))\
\
    tempInfo = heapq.heappop(pq)\
    field[tempInfo.r][tempInfo.c] = item[\cf9 0\cf4 ]\
\
answer = \cf9 0\
\
\cf2 for \cf4 i \cf2 in \cf8 range\cf4 (\cf9 1\cf2 , \cf4 N + \cf9 1\cf4 ):\
    \cf2 for \cf4 j \cf2 in \cf8 range\cf4 (\cf9 1\cf2 , \cf4 N + \cf9 1\cf4 ):\
        cnt = \cf9 0\
        \cf2 for \cf4 k \cf2 in \cf8 range\cf4 (\cf9 4\cf4 ):\
            nx = j + dx[k]\
            ny = i + dy[k]\
\
            \cf2 if \cf4 nx < \cf9 1 \cf2 or \cf4 nx > N \cf2 or \cf4 ny > N \cf2 or \cf4 ny < \cf9 1\cf4 :\
                \cf2 continue\
            for \cf4 l \cf2 in \cf4 newData[field[i][j]]:\
                \cf2 if \cf4 l == field[ny][nx]:\
                    cnt += \cf9 1\
        \cf2 if \cf4 cnt != \cf9 0\cf4 :\
            answer += \cf9 10 \cf4 ** (cnt - \cf9 1\cf4 )\
\
\cf8 print\cf4 (answer)\
\
}