var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

Notification.requestPermission().then(function(result) {
    console.log(result);
});

function connect(myId) {
    // username = document.querySelector('#name').value.trim();
    username = myId;
    var socket = new SockJS("/jpetstore_war/ws/chat");
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}


function onConnected() {
    console.log("Log In : " + username)
    stompClient.subscribe('/topic/chat/alarm/'+ username, onMessageReceived);
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.color = 'red';
}


function onMessageReceived(payload) {
    var data = JSON.parse(payload.body);
    let date = new Date().toLocaleString();
    let notification;
    let notificationPermission = Notification.permission;
    if (notificationPermission === "granted") {
        //Notification을 이미 허용한 사람들에게 보여주는 알람창
        if(username === data.receiver) {
            notification = new Notification(`새 메시지가 도착했습니다.`, {
                body: data.sender + " : " + data.content,
                icon: '../images/logo-topbar.gif',
            });
        }
    } else if (notificationPermission !== 'denied') {
        //Notification을 거부했을 경우 재 허용 창 띄우기
        Notification.requestPermission(function (permission) {
            if (permission === "granted") {
                notification = new Notification(`Hello,World!!😍`, {
                    body: `첫방문일시: ${date}`,
                    icon: "../images/logo-topbar.gif",
                });
            }else {
                alert("알람 허용이 거부되었습니다.")
            }
        });
    }
}

