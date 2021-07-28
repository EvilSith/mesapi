def validate(r):
    if r.status_code != 200:
        raise Exception("post_text error")