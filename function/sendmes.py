from conf.config import URL
import os
from function.validate import validate
import requests


def sendmes_telegram(text: str, chat_id):

    method = URL + os.getenv('TOKEN') + "/sendMessage"

    body = {
        "chat_id": chat_id,
        "text": text,
        "parse_mode": "markdown",
        "disable_web_page_preview": "true"
    }

    headers = {
        "user-agent": "Mozilla/5.0 (Windows NT 6.0; WOW64; rv:24.0) Gecko/20100101 Firefox/24.0"
    }

    response = requests.post(method, data=body, headers=headers)
    validate(response)
