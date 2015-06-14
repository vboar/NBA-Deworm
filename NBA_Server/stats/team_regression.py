# -*- coding:utf-8 -*-

__author__ = 'Vboar'

import numpy as np
from scipy import stats
from matplotlib import pyplot as plt

f = file('stats/reg_data.txt')

datas = np.loadtxt(f, delimiter=";", dtype=float)
x = datas[0]
y = datas[1]

slope, intercept, r_value, p_value, std_err = stats.linregress(x, y)
print slope, intercept, r_value, p_value, std_err

plt.plot(x, y, 'bo', color='#48CFAD')
plt.plot(x, slope * x + intercept, 'r-', color='#ED5565', linewidth=2)
plt.savefig('stats/reg.png')
