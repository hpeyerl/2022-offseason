# Automatic RGB565 Color Tracking Example
#
# This example shows off single color automatic RGB565 color tracking using the OpenMV Cam.

import sensor, image, time, json, pyb
print("Wait for Camera Setup")

sensor.reset()
sensor.set_pixformat(sensor.RGB565)
sensor.set_framesize(sensor.QVGA)
sensor.skip_frames(time = 2000)
sensor.set_auto_gain(False, gain_db=3) # gain in db
sensor.set_auto_whitebal(False)
sensor.set_auto_exposure(False, exposure_us=10000) # exposure in us
sensor.set_contrast(3) # range -3 to +3
sensor.set_brightness(1) # range -3 to +3
sensor.set_saturation(3) # range -3 to +3
sensor.skip_frames(time = 2000)


status = ("Running", "No Detection", "Error", "Too many detected", "No Distance")
clock = time.clock()
sensor.skip_frames(time = 2000)

threshold = [9, 65, 20, 63, 7, 51] # Middle L, A, B values.


def trackObject():
    clock.tick()
    img = sensor.snapshot()
    objects = 0
    distanceArray = []
    objArray = []
    objWidthArray = []
    objoutofFrame = False
    for obj in img.find_blobs([threshold], pixels_threshold=20, area_threshold=40, merge=True, margin=50):
        objects += 1
        if obj.code() == 1:
            img.draw_rectangle(obj.rect())
            img.draw_cross(obj.cx(), obj.cy())
            objPos = ((obj.cx() - 160) / 160) # position of object relative to center of image
            distance = (3.27*308.79)/obj.w()#distance =(width(object)*focal length)/pixels on screen**all cm
            distanceArray.append(distance)#append to an array
            objArray.append(objPos)#append to an array
            objWidthArray.append((obj.w()-160)/160)#append to an array

            if ((obj.w()/160)/2) + abs(objPos) > 1: #checks to see if object is out of the frame
                objoutofFrame = True


    objectjson = {}
    if len(distanceArray) >> 0:
        targetDistance = min(distanceArray)#returns the lowest value
        distanceArraySorted = sorted(distanceArray)#sorts the array from lowest to highest
        if len(distanceArray) >> 1:
            if(distanceArraySorted[1]-distanceArraySorted[0] < 0.4):
                index0 = distanceArray.index(distanceArraySorted[0])
                index1 = distanceArray.index(distanceArraySorted[1])
                if index0 < index1:#gets lowest value
                    targetDistance = distanceArraySorted[0]
                else:
                    targetDistance = distanceArraySorted[1]
        indexNum = distanceArray.index(targetDistance)

    if objoutofFrame:#checking to see if object is outside frame
        objectjson["status"] = status[4]
        objectjson["objectpos"] = objArray[indexNum]
        objectjson["distance"] = None
    elif objects > 0:
        objectjson["status"] = status[0]
        objectjson["objectpos"] = objArray[indexNum] # json int to stringT
        objectjson["distance"] = distanceArray[indexNum]
    elif objects == 0:
        objectjson["status"] = status[1]
        objectjson["objectpos"] = 0 # json int to stringT
        objectjson["distance"] = 0
    else:
        objectjson["status"] = status[2]

    print('%s' %json.dumps(objectjson))



while(True):
    trackObject()
