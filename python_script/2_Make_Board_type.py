import requests
import json

main_URL = "https://port-0-everytime-server-3vw25lceo19sl.gksl2.cloudtype.app"
main_URL = "http://127.0.0.1:8080"

def make_board(payload):
    url = main_URL + "/boardType/create"
    print(url)

    headers = {
        'Content-Type': 'application/json',
        'User-Agent': 'PostmanRuntime/7.30.0'
    }

    json_data = json.dumps(payload)

    response = requests.request(
        "POST", url, headers=headers, data=json_data)
    print(response.text.encode('utf8'))


payload = {
    "schoolName": "동아대학교",
    "boardType": "자유게시판",
    "boardDescription": "자유게시판",
    "boardTypeClass": "GENERAL"
}

arr = [
    ["자유게시판", "자유게시판", "GENERAL"],
    ["비밀게시판", "비밀게시판", "GENERAL"],
    ["졸업생게시판", "졸업생게시판", "GENERAL"],
    ["새내기게시판", "새내기게시판", "GENERAL"],
    ["시사·이슈", "시사·이슈", "GENERAL"],
    ["장터게시판", "장터게시판", "GENERAL"],
    ["정보게시판", "정보게시판", "GENERAL"],
    ["홍보게시판", "홍보게시판", "NOTICE"],
    ["동아리·학회", "동아리·학회", "NOTICE"],
    ["취업·진로", "취업·진로", "CAREER"],
    ["공과대학 학생회", "반갑습니다, 3100 ICT공대인들과 함께 최고의 한 해를 만들 준비가 되었습니다, 제9대 STAND BY ICT공과대학 학생회입니다.", "GROUP"],
    ["상경대학 학생회", "제 38대 All:ways 애국 상경대학 게시판입니다.", "GROUP"],
    ["간호학과 게시판", "자유게시판", "DEGREE"],
    ["한의학과 게시판", "자유게시판", "DEGREE"],
    ["어둠의 뽕나무숲", "🌌구인글 쓸꺼면 썩나가주세용🌝🌌", "ETC"],
    ["게임 같이하실분~~", "폰게임 컴게임 홍보가능해~", "ETC"],
    ["🙏소원 비는곳🙏", "🌟🙏 🐮☝️ 🙏🌟!", "ETC"],
    ["🏳️‍🌈❤️🧡💛💚💙💜🏳️‍🌈", "퀴어게시판!", "ETC"],
    ["❤️동의인 미팅 남자 소개 & 여자 소개💙", "소개 받고 솔탈 하자!", "ETC"],
    ["끝말잇기하는 게시판", "혼돈의 끝말잇기", "ETC"],
    ["🏋🏻헬스/운동 게시판🏋🏻‍♂️", "득근득근", "ETC"],
    ["불꽃의 화풀이방", "화를 풀어보세여, 욕, 뒷담도 ok -다른 사람 글이 시비는 ㄴ", "ETC"],
    ["덕질게시판", "자유롭게 덕질 할수 있고 덕질메이트를 찾을수 있는 공간", "ETC"],
    ["애니게시판", "애니,만화,라노벨,우타이테등 환영", "ETC"],
    ["롤 게시판", "리그 오브 레전드", "ETC"],
    ["노래추천", "당신의 인생곡을 추천해주세요🤍", "ETC"],
]

for i in range(len(arr)):
    payload["boardType"] = arr[i][0]
    payload["boardDescription"] = arr[i][1]
    payload["boardTypeClass"] = arr[i][2]
    make_board(payload)
