
from conf.config import URL
import os
import requests
from email.mime.multipart import MIMEMultipart

def senddoc_telegram(document, chat_id, caption):
    bound = MIMEMultipart('form-data', '----boundary')
    files = dict(bound)
    name_files = document.filename
    files['document']=(name_files, document.stream)

    method = URL + os.getenv('TOKEN') + "/sendDocument"

    response = requests.post(method, data={
        "chat_id": chat_id,
        "parse_mode": "markdown",
        "caption": caption
        }, files=files)
    response.raise_for_status()
