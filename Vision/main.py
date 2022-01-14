# Find Circles Example
#
# This example shows off how to find circles in the image using the Hough
# Transform. https://en.wikipedia.org/wiki/Circle_Hough_Transform
#
# Note that the find_circles() method will only find circles which are completely
# inside of the image. Circles which go outside of the image/roi are ignored...

import sensor, image, time

sensor.reset()
sensor.set_pixformat(sensor.RGB565) # grayscale is faster
sensor.set_framesize(sensor.QQVGA)
sensor.skip_frames(time = 2000)
clock = time.clock()
sensor.set_auto_exposure(False, exposure_us = 1000)
#sensor.set_quality(24)
#sensor.set_framesize(sensor.SXGA)
#sensor.set_framesize(sensor.QQVGA)

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

    for c in img.find_circles(threshold = 3280, x_margin = 10, y_margin = 10, r_margin = 10,
            r_min = 2, r_max = 1000, r_step = 2):
        img.draw_circle(c.x(), c.y(), c.r(), color = (0, 0, 0))
        #print(c)
        #print(img.get_pixel(c.x(), c.y()))
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
        #print(rgb)

        if (blueMid>100 and blueMid>(greenMid*2) and blueMid > (redMid * 2) and
            blueLeft>100 and blueLeft>(greenLeft*2) and blueLeft > (redLeft * 2) and
            blueRight>100 and blueRight>(greenRight*2) and blueRight > (redRight * 2)
            ):
            #print (rgbMid)
            #print(distance)
            #print (c.r())
            #print (c.r())
            xPos = (c.x()/80)-1
            yPos = 1-(c.y()/60)
            #print(xPos)
            #print(yPos)
            jsonstring["balltype"] = "blue"
            jsonstring["xpos"] = xPos
            jsonstring["ypos"] = yPos
            jsonstring["distance"] = distance
            print( "%s" %json.dumps(jsonstring))
            #print (c.r())

    #print("FPS %f" % clock.fps())



    #print("FPS %f" % clock.fps())
