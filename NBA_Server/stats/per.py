# -*- coding:utf-8 -*-

__author__ = 'Vboar'

import numpy as np
import matplotlib.pyplot as plt

f = file('stats/Per.txt')

line = f.readline().replace('\n', '')
temp = line.split(';')
abbr = temp[0]
season = temp[1]
data = np.loadtxt(f, delimiter=";", dtype=str)
names = data[0]
datas = []
temp = data[1]
for item in temp:
    datas.append(float(item))

for i in range(0, len(names)-1):
    for j in range(0, len(names)-1-i):
        if datas[j] < datas[j+1]:
            temp = names[j]
            names[j] = names[j+1]
            names[j+1] = temp
            temp = datas[j]
            datas[j] = datas[j+1]
            datas[j+1] = temp

ind = np.arange(len(names))


def autolabel(rects, label, num):
    # attach some text labels
    for rect in rects:
        height = rect.get_height()
        if num < 0:
            height = 0.1
        ax.text(rect.get_x()+rect.get_width()/2., 1.02*height, label,
                ha='center', va='bottom', fontsize=9, rotation=90)

fig, ax = plt.subplots()
for i in range(0, len(names)):
    rectsi = ax.bar([i], [datas[i]], 0.8, color='#ED5565', edgecolor='white')
    autolabel(rectsi, names[i], datas[i])
ax = plt.gca()
ax.set_xticks([])



plt.xlim(-0.3, len(names))
if min(datas) > 0:
    plt.ylim(0, max(datas)+8)
else:
    plt.ylim(min(datas)-2, max(datas)+8)
plt.savefig('stats/Per.png')
plt.savefig('stats/Per' + '_' + abbr + '_' + season + '.png')