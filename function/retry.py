import time
from datetime import datetime

def retry(function, retryAttempt, retryDelayInSeconds):
    last_error = None
    while retryAttempt > 0:
        try:
            return function()
        except Exception as ex:
            last_error = ex
            time.sleep(retryDelayInSeconds)
            retryAttempt -= 1
            print(f"{datetime.now()}: left attempts: {retryAttempt}")
    raise last_error
