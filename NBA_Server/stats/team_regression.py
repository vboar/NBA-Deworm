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

f = open('stats/team_regression_result.txt', 'w')
f.write(slope.__str__()+";"+intercept.__str__()+";"+r_value.__str__()
        +";"+p_value.__str__()+";"+std_err.__str__()+"\n")

plt.plot(x, y, 'bo', color='#48CFAD')
plt.plot(x, slope * x + intercept, 'r-', color='#ED5565', linewidth=2)
plt.savefig('stats/reg.png')
