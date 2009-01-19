var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"id":1149,"sl":30,"methods":[{"sl":35,"el":38,"sc":3},{"sl":40,"el":43,"sc":3},{"sl":45,"el":48,"sc":3},{"sl":50,"el":53,"sc":3},{"sl":55,"el":58,"sc":3},{"sl":60,"el":63,"sc":3},{"sl":65,"el":68,"sc":3},{"sl":70,"el":73,"sc":3}],"el":74,"name":"AttributeDictionary"}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_28":{"methods":[{"sl":35},{"sl":40},{"sl":45}],"name":"testServiceInjection","statements":[{"sl":37},{"sl":42},{"sl":47}],"pass":true},"test_26":{"methods":[{"sl":35},{"sl":50},{"sl":55},{"sl":60},{"sl":65},{"sl":70}],"name":"testAttributeDictionaryAdapter","statements":[{"sl":37},{"sl":52},{"sl":57},{"sl":62},{"sl":67},{"sl":72}],"pass":true},"test_9":{"methods":[{"sl":35},{"sl":40},{"sl":45}],"name":"testDecoratedServiceInjection","statements":[{"sl":37},{"sl":42},{"sl":47}],"pass":true}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [28, 9, 26], [], [28, 9, 26], [], [], [28, 9], [], [28, 9], [], [], [28, 9], [], [28, 9], [], [], [26], [], [26], [], [], [26], [], [26], [], [], [26], [], [26], [], [], [26], [], [26], [], [], [26], [], [26], [], []]
