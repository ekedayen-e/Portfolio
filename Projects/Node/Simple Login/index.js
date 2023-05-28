const express = require("express")
const bodyParser = require("body-parser")

const app = express();
const port = 3000; 
app.use(bodyParser.urlencoded({extended: false}));
app.use("/public", express.static(__dirname + "/public"));
app.post('/login', (req, res) => {
    let username = req.body.username;
    let password = req.body.password;
    if (username === 'admin' && password === 'admin') {
        res.send('Login successful');
    }
    else {
        res.send('Login failed');
    }
})

app.get("/", (req,res) => {
	res.sendFile("index.html", {root: __dirname});
})

app.listen(port, () => {
console.log(`Now listening on port ${port}`);
})