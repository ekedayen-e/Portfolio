import logo from './logo.svg';
import './App.css';
import {useState} from 'react'

let ops = ["+","-","/","*"]
let check = (x) => x== "+" || x == "-" || x =="/" || x == "*";
let digits = ["0","1","2","3","4","5","6","7","8","9"]
let others = ["1","2","3","4","5","6","7","8","9"]

function App() {

  const [state, setState] = useState({
    exp: ["0"],
    value: "",
    lastEntry: "0"});
    
  const handleChange = (x) => {

    let copy = [...state.exp] 
    let v  = state.value

    if(x in digits) {
      console.log('digit')
      if(state.lastEntry == "") {
        copy.push(x)
        setState({
          exp: copy,
          value: v,
          lastEntry: x
        })
        return;
      }

      else if(state.lastEntry[0] in digits) {
        if(copy[copy.length - 1][0] == "0" && x == "0" && (!copy[copy.length - 1].includes("."))) {
          return;
        }
        if(copy[copy.length - 1][0] == "0" && x in others) {
          setState({
            exp: [x],
            value: v,
            lastEntry: x
          })
          return;
        }
        if(copy[copy.length - 1][0] && x in digits && !copy[copy.length - 1].includes(".")) {
          copy[copy.length - 1] += x
        setState({
          exp: copy,
          value: v,
          lastEntry: x
        })
        return;
        }

        copy[copy.length - 1] += x
        setState({
          exp: copy,
          value: v,
          lastEntry: x
        })
        return;
      }

      else if(check(state.lastEntry)) {
        console.log("issue")
        copy.push(x)
        setState({
          exp: copy,
          value: v,
          lastEntry: x
        })
        return;
      }

      else if(state.lastEntry == ".") {
        if(copy[copy.length - 1].includes(".")) {
          copy[copy.length - 1] += x
          setState({
            exp: copy,
            value: v,
            lastEntry: x
          })
          return;
        }
      }
      else {
        return;
      }
    }

    if(check(x)) {
      console.log('ops')
      console.log(state.lastEntry + "This!")
      if(state.lastEntry == "") {
        return;
      }

      else if(state.lastEntry[0] in digits) {
        copy.push(x)
        setState({
          exp: copy,
          value: v,
          lastEntry: x
        })
      }

      else if(state.lastEntry == ".") {
        return;
      }

      else if(check(state.lastEntry)) {
          if(x == "-" && state.lastEntry == "*") {
            copy.push(x)
            setState({
            exp: copy,
            value: "1",
            lastEntry: x
          })
          return;
          }
          if(x == "+" && this.state.value == "1") {
            copy.pop()
            copy.pop()
            copy.push(x)
            setState({
              exp: copy,
              value: "",
              lastEntry: x
            })
            return;

          }
          copy.pop()
          copy.push(x)
          setState({
            exp: copy,
            value: v,
            lastEntry: x
          })
        }}

    if(x == "AC") {
      console.log("clear")
      setState({
        exp: ["0"],
        value: "",
        lastEntry: "0"
      })
    }

    if(x == ".") {
      if(copy[copy.length - 1].includes(".")) {
        return;
      }
      if(state.lastEntry === "") {
        return;
      }

      else if(check(state.lastEntry)) {
        return;
      }

      else if(state.lastEntry[0] in digits) {
        if(copy.includes(".")) {
          return ;
        }

        copy[copy.length - 1] += x
        setState({
          exp: copy,
          value: v,
          lastEntry: x
        })
      }

      else if(state.lastEntry == ".") {
        return;
      }
    }

    if(x == "=") {
      if(state.lastEntry == "") {
        return;
      }
      let complete = copy.join("")
      let evalu = eval(complete);
      setState({
        exp: [evalu.toString()],
        value: v,
        lastEntry: evalu.toString()
      })
    }
  }
    return (
      <div id="calculator">
        <div id="d-frame">
        <p id="display">{state.exp} </p>
          </div>
        <div id="frame">
      <button id="clear" class="btn" onClick={() => handleChange("AC")}>AC</button>
      <button class="btn" id="decimal" onClick={() => handleChange(".")}>.</button>
      <button class="btn" id="zero" onClick={() => handleChange("0")}>0</button>
      <button class="btn" id="one" onClick={() => handleChange("1")}>1</button>
      <button class="btn" id="two" onClick={() => handleChange("2")}>2</button>
      <button class="btn" id="three" onClick={() => handleChange("3")}>3</button>
      <button class="btn" id="four" onClick={() => handleChange("4")}>4</button>
      <button class="btn" id="five" onClick={() => handleChange("5")}>5</button>
      <button class="btn" id="six" onClick={() => handleChange("6")}>6</button>
      <button class="btn" id="seven" onClick={() => handleChange("7")}>7</button>
      <button class="btn" id="eight" onClick={() => handleChange("8")}>8</button>
      <button class="btn" id="nine" onClick={() => handleChange("9")}>9</button>
      <button class="btn" id="add" onClick={() => handleChange("+")}>+</button>
      <button class="btn" id="subtract" onClick={() => handleChange("-")}>-</button>
      <button class="btn" id="multiply" onClick={() => handleChange("*")}>*</button>
      <button class="btn" id="divide" onClick={() => handleChange("/")}>/</button>
      <button class="btn" id="equals" onClick={() => handleChange("=")}>=</button>
        </div>
        </div>
    )
  }

export default App;
