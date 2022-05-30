from conf.config import URL
import os
from function.validate import validate
import requests


def sendmes_telegram(text: str, chat_id):

    method = URL + os.getenv('TOKEN') + "/sendMessage"

    r = requests.post(method, data={
        "chat_id": chat_id,
        "text": text,
        "parse_mode": "markdown"
        "disable_web_page_preview": "true"
        })
    validate(r)
