let sock = new SockJS("/letter");
sock.onopen = () => console.log("Info : letter connected");
sock.onmessage = receivedMessage;

sock.onclose = () => console.log("Info : letter disconnected");


function receivedMessage(message)
{

    var data = JSON.parse(message.data);
    console.log(data);  
    if(data.purpose == "status")
    {
        $("#lblUserCnt").html("현재 연결된 유저 : " + data.userCnt);
        for(var i = 0; i < data.letterCnt; i++)
        {
            var letterHtml =  
            '<div style="border-style: solid; width: inherit;">'+
            '    <lable>제목 :' + data.letters[i].title + ' </lable> <label>영화 :' + data.letters[i].movie + ' </label><br>'+
            '    <label>내용 : ' + data.letters[i].content + '</label><br>'+
            '    <a href="/room/' + data.letters[i].roomId + '">참여하기</a><button>삭제하기</button>'+
            '</div>';
        }
        $("#letterList").html(letterHtml);

        for(var i = 0; i < data.roomCnt; i++)
        {
            var roomHtml =  
            '<div style="border-style: solid; width: inherit;">'+
            '    <lable>제목 :' + data.rooms[i].title + ' </lable> <label>영화 :' + data.rooms[i].movie + ' </label><br>'+
            '    <label>내용 : ' + data.rooms[i].content + '</label><br>'+
            '    <a href="/room/' + data.rooms[i].roomId + '">참여하기</a><button>삭제하기</button>'+
            '</div>';
        }
        $("#roomList").html(roomHtml);


    }

};

// 편지 보내기
function letterSend()
{
    var letter = {
        "purpose" : "createLetter",
        "title" : $("#sendTitle").val(),
        "movie" : selectedTitle,
        "content" : $("#sendContent").val(),
        "howMany" : $("#sendHowMany").val(),
    };
    
    console.log(JSON.stringify(letter))
    sock.send(JSON.stringify(letter));
}


function letterDelete(target)
{
    if(target == null)

    var json = {
        "purpose" : "deleteLetter",
        "target" : target,
        
    }

}


