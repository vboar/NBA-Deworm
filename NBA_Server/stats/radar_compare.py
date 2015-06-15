# -*- coding:utf-8 -*-

__author__ = 'Vboar'

import numpy as np
import matplotlib.pyplot as plt

f = file('stats/RadarCompare.txt')

data = np.loadtxt(f, delimiter=";", dtype=str)

name = [data[0][0], data[1][0]]
season = data[0][1]
regular = data[0][2]
temp1 = data[0][3:]
temp2 = data[1][3:]
data1 = []
data2 = []
for item1 in temp1:
    data1.append(float(item1))
for item2 in temp2:
    data2.append(float(item2))

labels = np.array(['Rebounds', 'Assists', 'Steals', 'Blocks', 'Turnovers', 'Personal Fouls'])

fig = plt.figure()
angles = np.linspace(0, 2*np.pi, 6, endpoint=False)
angles = np.concatenate((angles, [angles[0]]))

data1 = np.concatenate((data1, [data1[0]]))
ax = fig.add_subplot(111, polar=True)
ax.plot(angles, data1, 'b-', linewidth=1, color='#ED5565')
ax.fill(angles, data1, facecolor='#ED5565', alpha=0.25)

data2 = np.concatenate((data2, [data2[0]]))
ax = fig.add_subplot(111, polar=True)
ax.plot(angles, data2, 'b-', linewidth=1, color='#48CFAD')
ax.fill(angles, data2, facecolor='#48CFAD', alpha=0.25)

ax.set_thetagrids(angles * 180/np.pi, labels)
# ax.set_title(name[0] + ' VS ' + name[1] + ' - ' + season, va='baseline', color='b')
ax.grid(True)
ax.set_rlim(0, max(max(data1), max(data2))+1)
ax.legend((name[0], name[1]), loc=(0.9, 0.9), labelspacing=0.005)

plt.savefig('stats/RadarCompare.png')
plt.savefig('stats/RadarCompare_' + name[0] + '_' + name[1] + '_' + season + '_' + regular + '.png')