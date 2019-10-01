import React from 'react';
import "./StatisticPanel.css";
import {NoData} from "../../";

export default class StatisticPanel extends React.Component {

  render() {
    let {values} = this.props;
    return (
        <div className="StatisticPanel">
          {
            values ?
            Object.entries(values).map((kv, index) => {
              let [key, value] = kv;
              return (
                <div key={index} className={"statsRow"}>
                  <div className={"statsKey"}>{key}</div>
                  <div className={"statsValue"}>{value}</div>
                </div>
            )})
                : <NoData />
          }
        </div>
    );
  }

}