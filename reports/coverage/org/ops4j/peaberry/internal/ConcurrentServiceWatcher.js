var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"id":909,"sl":32,"methods":[{"sl":42,"el":46,"sc":3},{"sl":93,"el":98,"sc":3},{"sl":100,"el":118,"sc":3},{"sl":120,"el":128,"sc":3},{"sl":130,"el":133,"sc":3}],"el":134,"name":"ConcurrentServiceWatcher"},{"id":912,"sl":48,"methods":[{"sl":53,"el":57,"sc":5},{"sl":61,"el":76,"sc":5},{"sl":78,"el":90,"sc":5}],"el":91,"name":"ConcurrentServiceWatcher.TrackingExport"}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_3":{"methods":[{"sl":53},{"sl":93},{"sl":100}],"name":"testServiceInterception","statements":[{"sl":54},{"sl":56},{"sl":94},{"sl":97},{"sl":103},{"sl":104},{"sl":106},{"sl":111},{"sl":116},{"sl":117}],"pass":true},"test_26":{"methods":[{"sl":53},{"sl":61},{"sl":93},{"sl":100}],"name":"testServiceInjection","statements":[{"sl":54},{"sl":56},{"sl":64},{"sl":66},{"sl":67},{"sl":68},{"sl":71},{"sl":72},{"sl":94},{"sl":97},{"sl":103},{"sl":104},{"sl":106},{"sl":107},{"sl":111},{"sl":112},{"sl":116},{"sl":117}],"pass":true},"test_30":{"methods":[{"sl":42},{"sl":53},{"sl":61},{"sl":78},{"sl":93},{"sl":100},{"sl":120},{"sl":130}],"name":"testConcurrentWatcherEquality","statements":[{"sl":44},{"sl":45},{"sl":54},{"sl":56},{"sl":64},{"sl":66},{"sl":67},{"sl":68},{"sl":71},{"sl":80},{"sl":82},{"sl":83},{"sl":84},{"sl":89},{"sl":94},{"sl":97},{"sl":103},{"sl":104},{"sl":106},{"sl":107},{"sl":111},{"sl":116},{"sl":117},{"sl":122},{"sl":123},{"sl":124},{"sl":132}],"pass":true},"test_19":{"methods":[{"sl":53},{"sl":93},{"sl":100}],"name":"testDecoratedServiceInjection","statements":[{"sl":54},{"sl":56},{"sl":94},{"sl":97},{"sl":103},{"sl":104},{"sl":106},{"sl":107},{"sl":111},{"sl":116},{"sl":117}],"pass":true}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [30], [], [30], [30], [], [], [], [], [], [], [], [3, 30, 19, 26], [3, 30, 19, 26], [], [3, 30, 19, 26], [], [], [], [], [30, 26], [], [], [30, 26], [], [30, 26], [30, 26], [30, 26], [], [], [30, 26], [26], [], [], [], [], [], [30], [], [30], [], [30], [30], [30], [], [], [], [], [30], [], [], [], [3, 30, 19, 26], [3, 30, 19, 26], [], [], [3, 30, 19, 26], [], [], [3, 30, 19, 26], [], [], [3, 30, 19, 26], [3, 30, 19, 26], [], [3, 30, 19, 26], [30, 19, 26], [], [], [], [3, 30, 19, 26], [26], [], [], [], [3, 30, 19, 26], [3, 30, 19, 26], [], [], [30], [], [30], [30], [30], [], [], [], [], [], [30], [], [30], [], []]
