# -*- coding:utf-8 -*-

__author__ = 'Vboar'

import numpy as np
import matplotlib.pyplot as plt

f = file('stats/PlayerRadar.txt')

data = np.loadtxt(f, delimiter=";", dtype=str)

name = data[0]
season = data[1]
regular = data[2]
data = data[3:]

labels = np.array(['Assists', 'Steals', 'Blocks', 'Turnovers', 'Personal Fouls'])

angles = np.linspace(0, 2*np.pi, 5, endpoint=False)
data = np.concatenate((data, [data[0]]))
angles = np.concatenate((angles, [angles[0]]))

fig = plt.figure()
ax = fig.add_subplot(111, polar=True)
ax.plot(angles, data, 'bo-', linewidth=1, color='r')
ax.set_thetagrids(angles * 180/np.pi, labels, fontproperties="Yahei Consolas Hybrid")
ax.fill(angles, data, facecolor='r', alpha=0.25)
ax.set_title(name, va='bottom', fontproperties="Yahei Consolas Hybrid")
ax.grid(True)
plt.savefig('stats/PlayerRadar.png')