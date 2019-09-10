import React from 'react';

const LiStats = (props) => {
  return <li className="li-stats">{props.kv.key}: {props.kv.value}</li>
};

export default LiStats;