import requests
import json
import random
import getJWTtoken

token = getJWTtoken.getToken()

main_URL = "https://port-0-everytime-server-3vw25lceo19sl.gksl2.cloudtype.app"
#main_URL = "http://127.0.0.1:8080"

# 게시판 종류 가저오기 ex) 자유게시판 pk, 정보게시판 pk
def get_board_pk():
    url = main_URL + "/boardType/getBoardTypeBySchoolName"

    headers = {
        'Content-Type': 'application/json',
        'User-Agent': 'PostmanRuntime/7.30.0',
        'jwt': token
    }

    response = requests.request("GET", url, headers=headers)
    

    arr = json.loads(response.text)
    resultArr = []
    for i in arr:
        resultArr.append(i['boardTypePK'])

    return resultArr

print(get_board_pk())

boardPKs = get_board_pk()


# 게시판 글 PK 리스트 가져오기 (동의대-자유게시판 글 가져오기)
#GET /board/getBoardByBoardType/{boardTypeId}
def get_board_by_PK(boardTypePK): 
    url = main_URL + "/board/getBoardByBoardType/"+str(boardTypePK)

    headers = {
        'Content-Type': 'application/json',
        'User-Agent': 'PostmanRuntime/7.30.0',
        'jwt': token
    }
    response = requests.request("GET", url, headers=headers)
    print(response)

    arr = json.loads(response.text)
    #print(type(arr))
    resultArr = []
    if arr:
        for i in arr:
            resultArr.append(i['boardPK'])

    return resultArr






arr = [
    "우리는 나이가 들면서 변하는 게 아니다. 보다 자기 다뤄지는 것이다.",
    "게으르지 않음은 영원한 삶의 집이요, 게으름은 죽음의 집이다.",
    "나는 날마다, 모든 면에서, 점점 더 좋아지고 있다.",
    "인생에 뜻을 세우는데 적당한 때는 없다.",
    "실패는 잊어라. 하지만 그것이 준 교훈은 절대 잊으면 안 된다.",
    "우리는 행복하기 때문에 웃는 게 아니라, 웃기 때문에 행복하다.",
    "너 자신이 돼라. 다른 사람은 이미 있으니까.",
    "세상에 공짜는 없다.",
    "또 후회한들 무엇하랴.",
    "인생은 가까이서 보면 비극, 멀리서 보면 희극이다.",
    "승자는 시간을 관리하며 살고 패자는 시간에 끌려 산다.",
    "인생을 다시 산다면 다음번에는 더 많은 실수를 저지르리라.",
    "최고에 도달하려면 최저에서 시작해라.",
    "배움은 의무도, 생존도 아니다.",
    "행동의 가치는 그 행동을 끝까지 이루는 데 있다.",
    "아무것도 하지 않으면 아무 일도 일어나지 않는다.",
    "일단 시작해라. 나중에 완벽해지면 된다.",
    "인격은 그 사람의 운명이다.",
    "아는 것이 힘이다.",
    "자나 깨나 말조심.",
    "참을 인 세 번이면 호구다.",
    "겨울이 오면 봄이 멀지 않으리.",
    "내 비장의 무기는 아직 손안에 있다. 그것은 희망이다.",
    "모든 이의 마음을 얻으려고 계산된 글은 그 누구의 마음도 얻지 못한다.",
    "가장 큰 위험은 위험 없는 삶이다.",
    "오늘 할 수 있는 일을 내일로 미루지 마라.",
    "위험은 자신이 무엇을 하는지 모르는 데서 온다.",
    "모든 사람들로부터 사랑받지 않아도 된다.",
    "겉모습에 속지 마라.",
    "망설이면 두려움만 커진다.",
    "우리 자신에게 실패를 허락하는 것이 성공을 허락하는 것이다.",
    "미래를 만드는 건 현재다.",
    "못하는 일에 초점 맞추기를 그만하면 자기가 어떤 일을 할 수 있는지 알고 놀라게 될 것이다.",
    "글은 병든 마음 고치는 의사다.",
    "인내가 최상의 미덕이다.",
    "웃는 자에게 복이 온다.",
    "수학은 정답이 있지만 인생은 정답이 없더라.",
    "익숙함을 당연하게 생각하지 마세요.",
    "그리움과 분노는 한 끗 차이.",
    "삶이 가치가 있다고 믿어라.",
    "가난은 가난하다고 느끼는 곳에 존재한다.",
    "삶이 있는 한 희망은 있다.",
    "피할 수 없으면 즐겨라.",
    "계단을 밟아야 계단 위에 올라설 수 있다.",
    "행복은 습관이다.",
    "인생은 위험의 연속이다.",
    "아무런 위험을 감수하지 않는다면, 더 큰 위험을 감수하게 될 것이다.",
    "진흙 속에서 피는 꽃",
    "훌륭한 사람과 어리석은 사람의 차이는 불과 한 걸음 차이다.",
    "사람들은 할 말이 없으면 욕을 한다.",
    "정해진 것은 아무것도 없다. 정해진 운명 또한 없다.",
    "너의 운명의 별은 너의 마음속에 있다.",
    "같은 것을 놓고 좋아하고 싫어하는 것, 그것이 바로 진정한 우정이다.",
    "당신의 하루하루를 당신의 마지막 날이라고 생각하라.",
    "작은 기회로부터 위대한 업적이 시작된다.",
    "인생은 자전거를 타는 것과 같다. 쓰러지지 않으려면 뛰어야 한다.",
    "미래는 지금이다.",
    "실패는 성공을 돋보이게 하는 조미료.",
    "실패로부터 배울 수 있다면 그 실패는 성공이다.",
    "꿈을 꾸기에 인생은 빛난다.",
    "젊은이는 소망으로 살고, 노인은 추억으로 산다.",
    "길을 잃는다는 것은 곧, 길을 알게 되는 것이다.",
    "인생의 비결은 그것을 다루는 방법입니다.",
    "하는 것은 상상에서 비약적인 도약입니다.",
    "입을 연 이상 비밀은 없다.",
    "남 생각에 나를 맞추지 마세요.",
    "스스로 행복하다고 믿지 않으면, 그 누구도 행복할 수 없다.",
    "인생은 한 권의 책과 같다.",
    "변명 중에서 가장 어리석은 별명은 '시간이 없어서'이다.",
    "만남은 인연이다, 소중한 인연 소중하게 대하라.",
    "인내는 쓰다. 그러나 그 열매는 달다.",
    "인생은 풀어야 하는 문제가 아니라 경험해야 하는 현실이다.",
    "이 세상에서 자기 자신보다 사랑스러운 것은 없다.",
    "인생은 스스로 되풀이하면서 변화하는 모습의 연속이 아닐까?",
    "꿈을 꿀 수 있다면, 그 꿈을 실현할 수 있다.",
    "멈추지 말고 느리게라도 뛰어봐.",
    "오랫동안 꿈을 그리는 사람은 마침내 그 꿈을 닮아간다."]

payload = {
  "boardPK": 0,
  "nickname": "testID_0",
  "anonymity": True,
  "content": "string",
  "replyTo": 0,
  "userID": "testID_0"
}


def writecomment(comment):
    url = main_URL + "/boardComment/create"

    headers = {
        'Content-Type': 'application/json',
        'User-Agent': 'PostmanRuntime/7.30.0',
        'jwt': token
    }

    response = requests.request(
        "POST", url, headers=headers, data=comment)
    
    print(response)
    
comment_max = len(arr)

   
for i in boardPKs:
    print(i," PK가 가진 게시글 PK들",get_board_by_PK(i))


    for ii in get_board_by_PK(i):
        for _ in range(3):
            index = random.randint(0, comment_max-1)
            payload["boardPK"] = ii
            payload["content"] = arr[index]
            print(json.dumps(payload))
            
            writecomment( json.dumps(payload))
    
    

