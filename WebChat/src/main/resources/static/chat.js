
var stompClient = null;
var selectedUsername = document.querySelector('#friend').innerText.trim();
var from = document.querySelector('#username').innerText.trim();
var dialog = document.querySelector('#dialog').innerText.trim();


document.addEventListener("DOMContentLoaded", function () {
    connect(selectedUsername);
});

function connect(selectedUsername) {
    if (selectedUsername == "bot"){
        var socket = new SockJS("http://localhost:8090/bot");
        console.log("connecting to 8090")
    }
    else {
        socket = new SockJS("http://localhost:8080/chat");
        console.log("connecting to 8080")
    }
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user1/' + dialog, function (messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body));
        });
    }, function (err) {
        console.log('Connection lost: ' + err);
        document.getElementById('logout').submit();
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendMessage() {
    var text = document.getElementById('text').value;
    document.getElementById('text').value = '';
    if (selectedUsername == null) {
        alert('Please select a user.');
        return;
    }
    console.log(from);
    stompClient.send("/app/chat/" + dialog, {},
        JSON.stringify({'from': from, 'text': text, 'recipient': selectedUsername}));
}

function showMessageOutput(messageOutput) {
    console.log(JSON.stringify(messageOutput));
    if (messageOutput.from != from) {
        selectedUsername = messageOutput.from;
    }
    document.getElementById('response').innerHTML = document.getElementById('response').innerHTML + createTextNode(messageOutput);
}

function createTextNode(messageObj) {
    return '<div class= "row chat-message ' + (messageObj.myMsg ? ' my-message' : '') + '">' +
        '<div class="col-md-4">' +
        messageObj.from +
        '<br><small>' + messageObj.time + '</small>' +
        '</br></div>' +
        '<div class="col-md-8"><b>' +
        messageObj.text +
        '</b></div>' +
        '</div>';
}
