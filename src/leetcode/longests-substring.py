{\rtf1\ansi\ansicpg949\cocoartf2706
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fnil\fcharset0 Menlo-Regular;}
{\colortbl;\red255\green255\blue255;\red70\green137\blue204;\red255\green255\blue255;\red202\green202\blue202;
\red167\green197\blue152;\red194\green126\blue101;\red67\green192\blue160;\red212\green214\blue154;\red89\green138\blue67;
}
{\*\expandedcolortbl;;\cssrgb\c33725\c61176\c83922;\cssrgb\c100000\c100000\c100000\c7059;\cssrgb\c83137\c83137\c83137;
\cssrgb\c70980\c80784\c65882;\cssrgb\c80784\c56863\c47059;\cssrgb\c30588\c78824\c69020;\cssrgb\c86275\c86275\c66667;\cssrgb\c41569\c60000\c33333;
}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\deftab720
\pard\pardeftab720\partightenfactor0

\f0\fs26 \cf2 \cb3 \expnd0\expndtw0\kerning0
import\cf4  sys\
sys.setrecursionlimit(\cf5 10\cf4 **\cf5 6\cf4 )\
\pard\pardeftab720\partightenfactor0
\cf6 input\cf4  = sys.stdin.readline\
\
\pard\pardeftab720\partightenfactor0
\cf2 class\cf4  \cf7 Solution\cf4 :\
    \cf2 def\cf4  \cf8 longestSubstring\cf4 (self, s: \cf6 str\cf4 , k: \cf6 int\cf4 ) -> \cf6 int\cf4 :\
        \cf9 #\uc0\u44057 \u51008  \u47928 \u51088 \u44032  k\u48264  \u51060 \u49345  \u48152 \u48373 \u46104 \u45716  \u44032 \u51109  \u44596  \u48512 \u48516  \u47928 \u51088 \u50676 \cf4 \
        \cf2 if\cf4  \cf6 len\cf4 (s)==\cf5 0\cf4 : \cf2 return\cf4  \cf5 0\cf4 \
        \
        index = \cf5 0\cf4 \
        \cf2 while\cf4  \cf2 True\cf4 :\
            \cf2 if\cf4  s.count(s[index]) < k: \cf9 #\uc0\u51312 \u44148 \u50640  \u54644 \u45817 \u54616 \u51648  \u50506 \u45716  \u48512 \u48516 : \u45130 \u50612 \u51648 \u45716  \u44592 \u51456 \cf4 \
                \cf2 break\cf4 \
            \cf2 if\cf4  index == \cf6 len\cf4 (s)-\cf5 1\cf4 : \cf9 #\uc0\u45149 \u44620 \u51648  \u51312 \u44148 \u51012  \u53685 \u44284 \u54664 \u45796 \u47732 \cf4 \
                \cf2 return\cf4  \cf6 len\cf4 (s)\
            index += \cf5 1\cf4 \
\
        \cf2 return\cf4  \cf6 max\cf4 (self.longestSubstring(s[:index], k), self.longestSubstring(s[index+\cf5 1\cf4 :], k))\
    }