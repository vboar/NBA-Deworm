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

labels = np.array(['Rebounds', 'Assists', 'Steals', 'Blocks', 'Turnovers', 'Personal Fouls'])

angles = np.linspace(0, 2*np.pi, 6, endpoint=False)
data = np.concatenate((data, [data[0]]))
angles = np.concatenate((angles, [angles[0]]))

fig = plt.figure()
ax = fig.add_subplot(111, polar=True)
ax.plot(angles, data, 'b-', linewidth=1, color='#F44336')
ax.set_thetagrids(angles * 180/np.pi, labels)
ax.fill(angles, data, facecolor='#F44336', alpha=0.25)
# ax.set_title(name + ' - ' + season, va='baseline', color='b')
ax.grid(True)
plt.savefig('stats/PlayerRadar.png')
plt.savefig('stats/PlayerRadar_' + name + '_' + season + '_' + regular + '.png')