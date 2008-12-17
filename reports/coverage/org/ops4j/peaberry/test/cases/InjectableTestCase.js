var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"id":1664,"sl":40,"methods":[{"sl":49,"el":59,"sc":3},{"sl":52,"el":55,"sc":11},{"sl":61,"el":63,"sc":3},{"sl":65,"el":67,"sc":3},{"sl":69,"el":71,"sc":3},{"sl":73,"el":78,"sc":3},{"sl":80,"el":82,"sc":3},{"sl":84,"el":86,"sc":3},{"sl":88,"el":90,"sc":3}],"el":91,"name":"InjectableTestCase"}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_25":{"methods":[{"sl":61},{"sl":65},{"sl":73},{"sl":80},{"sl":84},{"sl":88}],"name":"testServiceExports","statements":[{"sl":62},{"sl":66},{"sl":74},{"sl":75},{"sl":81},{"sl":85},{"sl":89}],"pass":true},"test_32":{"methods":[{"sl":61},{"sl":65},{"sl":80},{"sl":88}],"name":"testDecoratedServiceInjection","statements":[{"sl":62},{"sl":66},{"sl":81},{"sl":89}],"pass":true},"test_15":{"methods":[{"sl":61},{"sl":80},{"sl":88}],"name":"testDirectServiceInjection","statements":[{"sl":62},{"sl":81},{"sl":89}],"pass":true}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [32, 15, 25], [32, 15, 25], [], [], [32, 25], [32, 25], [], [], [], [], [], [], [25], [25], [25], [], [], [], [], [32, 15, 25], [32, 15, 25], [], [], [25], [25], [], [], [32, 15, 25], [32, 15, 25], [], []]
