import requests
import json

main_URL = "https://port-0-everytime-server-3vw25lceo19sl.gksl2.cloudtype.app"
main_URL = "http://127.0.0.1:8080"

def getToken():
    url = main_URL + "/user/login"

    payload = {
        "userId": "testID_1",
        "userPwd": "testPW_1"
    }

    headers = {
        'Content-Type': 'application/json',
        'User-Agent': 'PostmanRuntime/7.30.0'
    }

    json_data = json.dumps(payload)

    response = requests.request(
        "POST", url, headers=headers, data=json_data)

    return response.text


print(getToken())
