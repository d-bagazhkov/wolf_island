import React from 'react';
import "./StatisticPanel.css";

export default class StatisticPanel extends React.Component {

  render() {
    let {values} = this.props;
    return (
        <div className="StatisticPanel">
          {
            Object.entries(values).map((kv, index) => {
              let [key, value] = kv;
              return (
                <div key={index} className={"statsRow"}>
                  <div className={"statsKey"}>{key}</div>
                  <div className={"statsValue"}>{value}</div>
                </div>
            )})
          }
        </div>
    );
  }

}