import requests
import json
import random

main_URL = "https://port-0-everytime-server-3vw25lceo19sl.gksl2.cloudtype.app"
main_URL = "http://127.0.0.1:8080"

def make_board(payload):
    url = main_URL + "/user/signup"
    print(url)

    headers = {
        'Content-Type': 'application/json',
        'User-Agent': 'PostmanRuntime/7.30.0'
    }

    json_data = json.dumps(payload)

    response = requests.request(
        "POST", url, headers=headers, data=json_data)
    print(response.status_code)
    print(response.text.encode('utf8'))


payload = {
  "userId": "string",
  "userName": "string",
  "userNickname": "string",
  "userEmail": "string",
  "userSchoolSchoolName": "string",
  "userRegisteredYear": 0,
  "userSchoolVerified": True,
  "userPoint": 0,
  "userStatus": "ACTIVE",
  "userPwd": "string"
}

for i in range(20):
    payload['userId'] = 'testID_' + str(i)
    payload['userName'] = 'tester_' + str(i)
    payload['userNickname'] = 'testNICKNAME_' + str(i)
    payload['userEmail'] = 'test' + str(i) + '@test.com'
    payload['userSchoolSchoolName'] = "동의대학교"
    payload['userRegisteredYear'] = random.randint(2010, 2021)
    payload['userSchoolVerified'] = True
    payload['userPoint'] = random.randint(0, 100)
    payload['userStatus'] = "ACTIVE"
    payload['userPwd'] = 'testPW_' + str(i)
    make_board(payload)

