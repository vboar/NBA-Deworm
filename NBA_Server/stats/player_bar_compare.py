# -*- coding:utf-8 -*-

__author__ = 'Vboar'

import numpy as np
import matplotlib.pyplot as plt

f = file('stats/PlayerBar-Compare.txt')

ind = np.arange(5)  # the x locations for the groups
width = 0.35       # the width of the bars

fig, ax = plt.subplots()
rects1 = ax.bar(ind, [5, 4, 3, 5, 6], width, color='r')
plt.show()