import os
import math
from PIL import Image as IMG
from PIL import ImageDraw, ImageFont


async def get_final_text_lines(text: str, text_width: int, font: ImageFont.FreeTypeFont) -> int:
    lines = text.split("\n")
    line_count = 0
    for line in lines:
        if not line:
            line_count += 1
            continue
        line_count += int(math.ceil(float(font.getsize(line)[0]) / float(text_width)))
    # print("lines: ", line_count + 1)
    return line_count


async def text_to_img(
        message: str,
        max_width: int = 1080,
        font_size: int = 40,
        spacing: int = 15,
        padding_x: int = 20,
        padding_y: int = 15,
        img_fixed: bool = False,
        font_name: str = "SimHei.ttf",
        save_name: str = "QQGameReturnMessage.png"
):
    filePath = os.path.dirname(os.path.abspath(__file__))
    font_path = filePath + "/" + font_name
    save_path = filePath + "/" + save_name
    font = ImageFont.truetype(font_path, font_size, encoding="utf-8")

    text_gather = message
    # print(max(font.getsize(text)[0] for text in text_gather.split("\n")) + 2 * padding_x)
    t = [font.getsize(text)[0] for text in text_gather.split('\n')]
    final_width = min(max(font.getsize(text)[0] for text in text_gather.split("\n")) + 2 * padding_x, max_width)
    text_width = final_width - 2 * padding_x
    text_height = (font_size + spacing) * await get_final_text_lines(text_gather, text_width, font)
    text_width = text_width + padding_x

    img_height_sum = 0
    final_height = 2 * padding_y + text_height + img_height_sum

    picture = IMG.new('RGB', (final_width, final_height), (255, 255, 255))
    draw = ImageDraw.Draw(picture)
    present_x = padding_x
    present_y = padding_y
    for char in message:
        if char == "\n":
            present_y += (font_size + spacing)
            present_x = padding_x
            continue
        if char == "\r":
            continue
        if present_x + font.getsize(char)[0] > text_width:
            present_y += (font_size + spacing)
            present_x = padding_x
        draw.text((present_x, present_y), char, font=font, fill=(0, 0, 0))
        present_x += font.getsize(char)[0]
    present_y += (font_size + spacing)

    picture.save(save_path)
