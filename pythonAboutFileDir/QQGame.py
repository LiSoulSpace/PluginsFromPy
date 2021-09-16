import os
import asyncio
import sys
filePath = os.path.dirname(os.path.abspath(__file__))
sys.path.append(filePath)
import util_main


async def qqGame():
    print(filePath)
    f = open(filePath + "/tempText.txt")
    t = f.read()
    message = t
    await util_main.text_to_img(message=message, save_name="QQGameReturnMessage.png")


if __name__ == '__main__':
    loop = asyncio.get_event_loop()
    loop.run_until_complete(qqGame())
