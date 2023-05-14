let time = [];
for (let i = 59; i >= 1; i--) {
  if (i < 10) {
    time.push("0" + i.toString());
  } else {
    time.push(i.toString());
  }
}
time.unshift("00");

let min = [];
for (let i = 60; i >= 0; i--) {
  if (i < 10) {
    min.push("0" + i.toString());
  } else {
    min.push(i.toString());
  }
}

let str = x => x ? "Break" : "Session";
var interval;
const beep = new Audio("https://raw.githubusercontent.com/freeCodeCamp/cdn/master/build/testable-projects-fcc/audio/BeepSound.wav");


var oper = false;

class Clock extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      breakVal: 5,
      sessionVal: 25,
      swap: false,
      state: str(false),
      timeLeft: min.indexOf("25"),
      sec: 0 };

  }

  manageClock() {
    oper = !oper;
    if (oper) {
      interval = setInterval(() => {
        if (this.state.timeLeft ==
        min.length - 1 && this.state.sec ==
        time.length - 1 && this.state.state == "Session") {
          this.setState(state => ({
            breakVal: state.breakVal,
            sessionVal: state.sessionVal,
            swap: state.swap,
            state: "Break",
            timeLeft: min.length - 1 - state.breakVal,
            sec: 0 }));

          beep.play();
        } else

        if (this.state.timeLeft ==
        min.length - 1 && this.state.sec ==
        time.length - 1 && this.state.state == "Break") {
          this.setState(state => ({
            breakVal: state.breakVal,
            sessionVal: state.sessionVal,
            swap: state.swap,
            state: "Session",
            timeLeft: min.length - 1 - state.sessionVal,
            sec: 0 }));

          beep.play();
        } else

        if (this.state.sec == 0) {
          this.setState(state => ({
            breakVal: state.breakVal,
            sessionVal: state.sessionVal,
            swap: state.swap,
            state: state.state,
            timeLeft: state.timeLeft + 1,
            sec: 1 }));

          console.log(this.state.sec);
        } else

        if (this.state.sec > 0 && this.state.sec <= time.length - 1) {
          this.setState(state => ({
            breakVal: state.breakVal,
            sessionVal: state.sessionVal,
            swap: state.swap,
            state: state.state,
            timeLeft: state.timeLeft,
            sec: state.sec + 1 }));

        } else

        if (this.state.sec > time.length - 1) {
          this.setState(state => ({
            breakVal: state.breakVal,
            sessionVal: state.sessionVal,
            swap: state.swap,
            state: state.state,
            timeLeft: state.timeLeft,
            sec: 0 }));

        }
      }, 1000);
    } else {
      clearInterval(interval);
    }
    return;
  }

  restart() {
    oper = false;
    clearInterval(interval);
    this.setState({
      breakVal: 5,
      sessionVal: 25,
      swap: false,
      state: str(false),
      timeLeft: min.indexOf("25"),
      sec: 0 });

  }

  increment(x) {
    if (oper) {
      return;
    }
    switch (x) {
      case "ib":
        this.setState(state => {
          if (state.breakVal < 60) {
            return {
              breakVal: state.breakVal + 1,
              sessionVal: state.sessionVal,
              timeLeft: state.timeLeft,
              swap: state.swap,
              state: state.state,
              sec: state.sec };

          }
        });
        break;
      case "is":
        this.setState(state => {
          if (state.sessionVal < 60) {
            return {
              breakVal: state.breakVal,
              sessionVal: state.sessionVal + 1,
              timeLeft: state.timeLeft - 1,
              swap: state.swap,
              state: state.state,
              sec: state.sec };

          }
        });
        break;
      default:
        return;}

  }

  decrement(x) {
    if (oper) {
      return;
    }
    switch (x) {
      case "db":
        this.setState(state => {
          if (state.breakVal > 1) {
            return {
              breakVal: state.breakVal - 1,
              sessionVal: state.sessionVal,
              timeLeft: state.timeLeft,
              swap: state.swap,
              state: state.state,
              sec: state.sec };

          }
        });
        break;
      case "ds":
        this.setState(state => {
          if (state.sessionVal > 1) {
            return {
              breakVal: state.breakVal,
              sessionVal: state.sessionVal - 1,
              timeLeft: state.timeLeft + 1,
              swap: state.swap,
              state: state.state,
              sec: state.sec };

          }
        });
        break;
      default:
        return;}

  }


  render() {
    return /*#__PURE__*/(
      React.createElement("div", { id: "frame" }, /*#__PURE__*/
      React.createElement("h1", null, "25 + 5 Clock"), /*#__PURE__*/

      React.createElement("div", { id: "row" }, /*#__PURE__*/
      React.createElement("div", { className: "column" }, /*#__PURE__*/

      React.createElement("p", { id: "break-label" }, /*#__PURE__*/React.createElement("strong", null, "Break Length")), /*#__PURE__*/
      React.createElement("span", null, /*#__PURE__*/
      React.createElement("button", { id: "break-decrement", className: "btn", onClick: () => this.decrement("db").bind(this) }, /*#__PURE__*/React.createElement("i", { className: "fa fa-arrow-down" })), /*#__PURE__*/

      React.createElement("span", { id: "break-length" }, this.state.breakVal), /*#__PURE__*/


      React.createElement("button", { id: "break-increment", className: "btn", onClick: () => this.increment("ib").bind(this) }, /*#__PURE__*/React.createElement("i", { className: "fa fa-arrow-up " })))), /*#__PURE__*/


      React.createElement("div", { className: "column" }, /*#__PURE__*/

      React.createElement("p", { id: "session-label" }, /*#__PURE__*/React.createElement("strong", null, "Session Length")), /*#__PURE__*/
      React.createElement("span", null, /*#__PURE__*/
      React.createElement("button", { id: "session-decrement", className: "btn", onClick: () => this.decrement("ds").bind(this) }, /*#__PURE__*/React.createElement("i", { className: "fa fa-arrow-down" })), /*#__PURE__*/

      React.createElement("span", { id: "session-length" }, this.state.sessionVal), /*#__PURE__*/


      React.createElement("button", { id: "session-increment", className: "btn", onClick: () => this.increment("is").bind(this) }, /*#__PURE__*/React.createElement("i", { className: "fa fa-arrow-up " }))))), /*#__PURE__*/



      React.createElement("div", { id: "timer" }, /*#__PURE__*/
      React.createElement("h4", { id: "timer-label" }, this.state.state), /*#__PURE__*/
      React.createElement("p", { id: "time-left" }, min[this.state.timeLeft], ":", time[this.state.sec]), /*#__PURE__*/
      React.createElement("span", null, /*#__PURE__*/
      React.createElement("button", { onClick: this.manageClock.bind(this), id: "start_stop", class: "btn" }, /*#__PURE__*/React.createElement("i", { class: "fa fa-play-circle-o" })), /*#__PURE__*/
      React.createElement("button", { id: "reset", onClick: this.restart.bind(this), class: "btn" }, /*#__PURE__*/React.createElement("i", { class: "fa fa-refresh" }))))));




  }}


ReactDOM.render( /*#__PURE__*/React.createElement(Clock, null), document.getElementById("app"));