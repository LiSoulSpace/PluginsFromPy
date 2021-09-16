import aiohttp
import asyncio
import sys
import os
filePath = os.path.dirname(os.path.abspath(__file__))
sys.path.append(filePath)
import util_main


async def get_weibo_hot():
    url = "http://api.weibo.cn/2/guest/search/hot/word"
    async with aiohttp.ClientSession() as session:
        async with session.get(url=url) as resp:
            data = await resp.json()
    data = data["data"]
    text_list = ["微博实时热榜:"]
    index = 0
    for i in data:
        index += 1
        text_list.append("\n%d. %s" % (index, i["word"].strip()))
    text = "".join(text_list).replace("#", "")
    print(text)
    await util_main.text_to_img(message=text, save_name="weiboHotSearch.png")


if __name__ == '__main__':
    loop = asyncio.get_event_loop()
    loop.run_until_complete(get_weibo_hot())
