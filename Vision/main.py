# Find Circles Example
#
# This example shows off how to find circles in the image using the Hough
# Transform. https://en.wikipedia.org/wiki/Circle_Hough_Transform
#
# Note that the find_circles() method will only find circles which are completely
# inside of the image. Circles which go outside of the image/roi are ignored...

import sensor, image, time, json

sensor.reset()
sensor.set_pixformat(sensor.RGB565) # grayscale is faster
sensor.set_framesize(sensor.QQVGA)
sensor.skip_frames(time = 2000)
clock = time.clock()
sensor.set_auto_exposure(False, exposure_us = 1000)

while(True):
    clock.tick()
    img = sensor.snapshot().lens_corr(1.8)

    # Circle objects have four values: x, y, r (radius), and magnitude. The
    # magnitude is the strength of the detection of the circle. Higher is
    # better...

    # `threshold` controls how many circles are found. Increase its value
    # to decrease the number of circles detected...

    # `x_margin`, `y_margin`, and `r_margin` control the merging of similar
    # circles in the x, y, and r (radius) directions.

    # r_min, r_max, and r_step control what radiuses of circles are tested.
    # Shrinking the number of tested circle radiuses yields a big performance boost.

    for c in img.find_circles(threshold = 3280, x_margin = 20, y_margin = 20, r_margin = 10,
            r_min = 1, r_max = 2000, r_step = 2):
        img.draw_circle(c.x(), c.y(), c.r(), color = (0, 0, 0))
        distance = (24 * 166.67)/(c.r()*2)

        rgbMid = img.get_pixel(c.x(), c.y());
        redMid = rgbMid[0]
        greenMid = rgbMid[1]
        blueMid = rgbMid[2]

        rgbLeft = img.get_pixel(c.x()-(int)(c.r()/2), c.y());
        redLeft = rgbLeft[0]
        greenLeft = rgbLeft[1]
        blueLeft = rgbLeft[2]

        rgbRight = img.get_pixel(c.x()+(int)(c.r()/2), c.y());
        redRight = rgbRight[0]
        greenRight = rgbRight[1]
        blueRight = rgbRight[2]

        jsonstring = {}
        if (redMid>100 and redMid>(greenMid*2) and redMid > (blueMid * 2) and
            redLeft>100 and redLeft>(greenLeft*2) and redLeft > (blueLeft * 2) and
            redRight>100 and redRight>(greenRight*2) and redRight > (blueRight * 2)
            ):
            #print("red")
            #print (rgbMid)
            #print(distance)
            #print (c.r())
            xPos = (c.x()/80)-1
            yPos = 1-(c.y()/60)
            #print(xPos)
            #print(yPos)
            jsonstring["balltype"] = "red"
            jsonstring["xpos"] = xPos
            jsonstring["ypos"] = yPos
            jsonstring["distance"] = distance
            print( "%s" %json.dumps(jsonstring))
            #print (c.r())

    #print("FPS %f" % clock.fps())
