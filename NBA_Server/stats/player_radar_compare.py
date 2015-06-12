# -*- coding:utf-8 -*-

__author__ = 'Vboar'

import numpy as np
import matplotlib.pyplot as plt

f = file('stats/PlayerRadar-Compare.txt')

data = np.loadtxt(f, delimiter=";", dtype=str)

name = [data[0][0], data[1][0]]
season = data[0][1]
regular = data[0][2]
data = [data[0][3:], data[1][3:]]

labels = np.array(['Rebounds', 'Assists', 'Steals', 'Blocks', 'Turnovers', 'Personal Fouls'])

fig = plt.figure()
angles = np.linspace(0, 2*np.pi, 6, endpoint=False)
angles = np.concatenate((angles, [angles[0]]))

item = data[0]
item = np.concatenate((item, [item[0]]))
ax = fig.add_subplot(111, polar=True)
ax.plot(angles, item, 'b-', linewidth=1, color='#F44336')
ax.fill(angles, item, facecolor='#F44336', alpha=0.25)

item = data[1]
item = np.concatenate((item, [item[0]]))
ax = fig.add_subplot(111, polar=True)
ax.plot(angles, item, 'b-', linewidth=1, color='#009688')
ax.fill(angles, item, facecolor='#009688', alpha=0.25)

ax.set_thetagrids(angles * 180/np.pi, labels)
# ax.set_title(name[0] + ' VS ' + name[1] + ' - ' + season, va='baseline', color='b')
ax.grid(True)
ax.legend((name[0], name[1]), loc=(0.9, 0.9), labelspacing=0.005)

plt.savefig('stats/PlayerRadar-Compare.png')
plt.savefig('stats/PlayerRadar-Compare_' + name[0] + '_' + name[1] + '_' + season + '_' + regular + '.png')