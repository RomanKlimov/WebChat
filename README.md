<h1>User-to-User chat with spring web socket, Sock Js and Stomp protocol</h1>
<br/>
<h2>Features:</h2>
<ul>
    <li>Implemented chat-bot which sends in response to any message a random article from Habrahabr.com</li>
    <li>Generating avatars of users, depending on their name and surname.</li>
</ul>
<br/>
<h2>Deployment information</h2>
<h3>Tested environment</h3>
<ol>
    <li>Java version: JDK 8</li>
    <li>DataBase: Postgresql 9.6</li>
    <li>Build tool: Maven</li>
</ol>
<h3>Chat deploying steps</h3>
<ol>
    <li>Create database with name "webchat"</li>
    <li>Configure file application.properties</li>
    <li>Configure connect function in chat.js file, if your port is not "8080"</li>
    <li>Cd into project WebChat</li>
    <li>Build project with mvn spring-boot:run</li>
    <li>For sign in you can use username and password</li>
    <li>Username:roman Password:roman</li>
    <li>Username:max Password:max</li>
    <li>Username:misha Password:misha</li>
</ol>

<h3>Bot deploying steps</h3>
<ol>
    <li>Cd into project ChatBot</li>
    <li>Build project with mvn spring-boot:run</li>
</ol>



