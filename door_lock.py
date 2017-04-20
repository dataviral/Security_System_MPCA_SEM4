import qrtools
import threading
import sys

ip = 0

class camThread (threading.Thread):

    def __init__(self, threadID):
        self.threadID = threadID
        self.thread = threading.Thread(target = self.run)
        self.thread.daemon = True

    def start(self):
        self.thread.start()

    def run(self):
        qr = qrtools.QR()
        qr.decode_webcam(self.handler)

    def handler(self, data):
        print(data)
        sys.stdout.flush()

    def wait(self):
        try:
            while 1:
                pass
        except Exception as e:
            pass

    def kill(self):
        self.thread.stop()


cam = camThread(1)

cam.start()
cam.wait()
