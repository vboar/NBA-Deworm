import numpy as np
import matplotlib.pyplot as plt
import sys


labels = np.array(['Point', 'Bound', 'Assist', 'Block', 'Steal'])

dataLenth = 5
#
point = sys.argv[1]
bound = sys.argv[2]
assist = sys.argv[3]
block = sys.argv[4]
steal = sys.argv[5]

data = np.array([point,bound,assist,block,steal])
#

angles = np.linspace(0, 2 * np.pi, dataLenth, endpoint=False)
data = np.concatenate((data, [data[0]]))  # 
angles = np.concatenate((angles, [angles[0]]))  # 

fig = plt.figure()
ax = fig.add_subplot(111, polar=True)  # 
ax.plot(angles, data, 'bo-', linewidth=2)  # 
ax.fill(angles, data, facecolor='r', alpha=0.25)  # 
ax.set_thetagrids(angles * 180 / np.pi, labels, fontproperties="SimHei")
ax.set_title("data", va='bottom', fontproperties="SimHei")
ax.set_rlim(0, 35)
ax.grid(True)
plt.savefig(sys.path[0]+"/radar.png", dpi=50, facecolor='w', edgecolor='w',
     orientation='portrait', papertype=None, format=None,
     transparent=True, bbox_inches=None, pad_inches=0.1,
     frameon=None)
#plt.show()
