# -*- coding:utf-8 -*-

__author__ = 'Vboar'

import numpy as np
import matplotlib.pyplot as plt

f = file('stats/LineChart.txt')

line = f.readline().replace('\n', '')
temp = line.split(';')
name = temp[0]
regular = temp[1]
filter_type = temp[2]

data = np.loadtxt(f, delimiter=";", dtype=str)
seasons = data[0]
temp = data[1]
datas = []
for item in temp:
    datas.append(float(item))

if len(seasons[0]) == 10:
    for i in range(0, len(seasons)):
        seasons[i] = seasons[i][5:]

seasons = np.array(seasons)
datas = np.array(datas)
avg = np.mean(datas)
avgs = []
for item in seasons:
    avgs.append(avg)

fig = plt.figure()

x_labels = []
temp = 1
if 30 < len(seasons) < 50:
    temp = 2
elif len(seasons) > 50:
    temp = 3
for i in range(0, len(seasons)):
    if i % temp == 0:
        x_labels.append(seasons[i])
    else:
        x_labels.append("")


# 设置横坐标
a = []
count = 0
for item in seasons:
    a.append(count)
    count += 1
plt.xticks(a, x_labels)
# plt.axis([0, len(a), 0, max(datas)+1])
axis = plt.gca().xaxis
for label in axis.get_ticklabels():
    label.set_rotation(80)
    label.set_fontsize(11)


avg_line, = plt.plot(a, avgs, 'r--', color='b', linewidth=1)
ca_line, = plt.plot(a, datas, 'b-', color='#F44336', linewidth=2)
fig.legend((avg_line, ca_line), ('average', 'career'), loc=(0.71, 0.91), labelspacing=0.005)
plt.savefig('stats/LineChart.png', dpi=60)
plt.savefig('stats/LineChart' + '_' + name + '_' + regular + '_' + filter_type + '.png')