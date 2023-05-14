let ops = ["+", "-", "/", "*"];
let check = x => x == "+" || x == "-" || x == "/" || x == "*";
let digits = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];
let others = ["1", "2", "3", "4", "5", "6", "7", "8", "9"];
class Calculator extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      exp: ["0"],
      value: "",
      lastEntry: "0" };

    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(x) {

    let copy = [...this.state.exp];
    let v = this.state.value;

    if (x in digits) {
      console.log('digit');
      if (this.state.lastEntry == "") {
        copy.push(x);
        this.setState({
          exp: copy,
          value: v,
          lastEntry: x });

        return;
      } else

      if (this.state.lastEntry[0] in digits) {
        if (copy[copy.length - 1][0] == "0" && x == "0" && !copy[copy.length - 1].includes(".")) {
          return;
        }
        if (copy[copy.length - 1][0] == "0" && x in others) {
          this.setState({
            exp: [x],
            value: v,
            lastEntry: x });

          return;
        }
        if (copy[copy.length - 1][0] && x in digits && !copy[copy.length - 1].includes(".")) {
          copy[copy.length - 1] += x;
          this.setState({
            exp: copy,
            value: v,
            lastEntry: x });

          return;
        }

        copy[copy.length - 1] += x;
        this.setState({
          exp: copy,
          value: v,
          lastEntry: x });

        return;
      } else

      if (check(this.state.lastEntry)) {
        console.log("issue");
        copy.push(x);
        this.setState({
          exp: copy,
          value: v,
          lastEntry: x });

        return;
      } else

      if (this.state.lastEntry == ".") {
        if (copy[copy.length - 1].includes(".")) {
          copy[copy.length - 1] += x;
          this.setState({
            exp: copy,
            value: v,
            lastEntry: x });

          return;
        }
      } else
      {
        return;
      }
    }

    if (check(x)) {
      console.log('ops');
      console.log(this.state.lastEntry + "This!");
      if (this.state.lastEntry == "") {
        return;
      } else

      if (this.state.lastEntry[0] in digits) {
        copy.push(x);
        this.setState({
          exp: copy,
          value: v,
          lastEntry: x });

      } else

      if (this.state.lastEntry == ".") {
        return;
      } else

      if (check(this.state.lastEntry)) {
        if (x == "-" && this.state.lastEntry == "*") {
          copy.push(x);
          this.setState({
            exp: copy,
            value: "1",
            lastEntry: x });

          return;
        }
        if (x == "+" && this.state.value == "1") {
          copy.pop();
          copy.pop();
          copy.push(x);
          this.setState({
            exp: copy,
            value: "",
            lastEntry: x });

          return;

        }
        copy.pop();
        copy.push(x);
        this.setState({
          exp: copy,
          value: v,
          lastEntry: x });

      }}

    if (x == "AC") {
      console.log("clear");
      this.setState({
        exp: ["0"],
        value: "",
        lastEntry: "0" });

    }

    if (x == ".") {
      if (copy[copy.length - 1].includes(".")) {
        return;
      }
      if (this.state.lastEntry === "") {
        return;
      } else

      if (check(this.state.lastEntry)) {
        return;
      } else

      if (this.state.lastEntry[0] in digits) {
        if (copy.includes(".")) {
          return;
        }

        copy[copy.length - 1] += x;
        this.setState({
          exp: copy,
          value: v,
          lastEntry: x });

      } else

      if (this.state.lastEntry == ".") {
        return;
      }
    }

    if (x == "=") {
      if (this.state.lastEntry == "") {
        return;
      }
      let complete = copy.join("");
      let evalu = eval(complete);
      this.setState({
        exp: [evalu.toString()],
        value: v,
        lastEntry: evalu.toString() });

    }
  }
  render() {
    console.log(this.state.exp);
    return /*#__PURE__*/(
      React.createElement("div", { id: "calculator" }, /*#__PURE__*/
      React.createElement("div", { id: "d-frame" }, /*#__PURE__*/
      React.createElement("p", { id: "display" }, this.state.exp, " ")), /*#__PURE__*/

      React.createElement("div", { id: "frame" }, /*#__PURE__*/
      React.createElement("button", { id: "clear", class: "btn", onClick: () => this.handleChange("AC") }, "AC"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "decimal", onClick: () => this.handleChange(".") }, "."), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "zero", onClick: () => this.handleChange("0") }, "0"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "one", onClick: () => this.handleChange("1") }, "1"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "two", onClick: () => this.handleChange("2") }, "2"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "three", onClick: () => this.handleChange("3") }, "3"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "four", onClick: () => this.handleChange("4") }, "4"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "five", onClick: () => this.handleChange("5") }, "5"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "six", onClick: () => this.handleChange("6") }, "6"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "seven", onClick: () => this.handleChange("7") }, "7"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "eight", onClick: () => this.handleChange("8") }, "8"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "nine", onClick: () => this.handleChange("9") }, "9"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "add", onClick: () => this.handleChange("+") }, "+"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "subtract", onClick: () => this.handleChange("-") }, "-"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "multiply", onClick: () => this.handleChange("*") }, "*"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "divide", onClick: () => this.handleChange("/") }, "/"), /*#__PURE__*/
      React.createElement("button", { class: "btn", id: "equals", onClick: () => this.handleChange("=") }, "="))));




  }}


ReactDOM.render( /*#__PURE__*/
React.createElement(Calculator, null),
document.getElementById('container'));