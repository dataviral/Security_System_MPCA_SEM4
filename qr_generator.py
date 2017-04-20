import sys
from random import randint
import pyqrcode
import os

try:

    u_name = sys.argv[1]
    temp_key = u_name + str(randint(0, 100000) )
    qr = pyqrcode.create(temp_key)
    qr.png("image/"+temp_key+".png", scale=15)
    print(temp_key)

except Exception as e:
    print("Error occured : ", e)
