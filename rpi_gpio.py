import RPi.GPIO as GPIO
import sys

deg = sys.argv[1]

GPIO.setmode(GPIO.BOARD)
servoPin = 11

GPIO.setup(servoPin, GPIO.OUT)
pwm = GPIO.PWM(servoPin,50)

def angle(x):
    return 3 + 11 * x / 180

pwm.ChangeDutyCycle(angle(deg))

GPIO.clenup()
