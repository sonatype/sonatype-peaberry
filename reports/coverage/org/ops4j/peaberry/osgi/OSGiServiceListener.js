var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"id":881,"sl":45,"methods":[{"sl":56,"el":67,"sc":3},{"sl":69,"el":88,"sc":3},{"sl":90,"el":106,"sc":3},{"sl":108,"el":120,"sc":3},{"sl":122,"el":127,"sc":3},{"sl":129,"el":145,"sc":3},{"sl":147,"el":164,"sc":3},{"sl":166,"el":173,"sc":3},{"sl":175,"el":188,"sc":3},{"sl":190,"el":203,"sc":3}],"el":204,"name":"OSGiServiceListener"}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_4":{"methods":[{"sl":56},{"sl":69},{"sl":122},{"sl":129},{"sl":175},{"sl":190}],"name":"testServiceLookupPerformance","statements":[{"sl":57},{"sl":59},{"sl":62},{"sl":65},{"sl":66},{"sl":70},{"sl":73},{"sl":76},{"sl":77},{"sl":80},{"sl":81},{"sl":124},{"sl":125},{"sl":131},{"sl":133},{"sl":135},{"sl":138},{"sl":178},{"sl":182},{"sl":183},{"sl":187},{"sl":193},{"sl":196},{"sl":197},{"sl":198},{"sl":202}],"pass":true},"test_12":{"methods":[{"sl":122},{"sl":175},{"sl":190}],"name":"testProxyPerformance","statements":[{"sl":124},{"sl":125},{"sl":178},{"sl":182},{"sl":183},{"sl":187},{"sl":193},{"sl":196},{"sl":197},{"sl":198},{"sl":202}],"pass":true},"test_11":{"methods":[{"sl":90},{"sl":129},{"sl":147},{"sl":166},{"sl":175},{"sl":190}],"name":"testServiceExports","statements":[{"sl":91},{"sl":93},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":101},{"sl":102},{"sl":131},{"sl":133},{"sl":135},{"sl":138},{"sl":139},{"sl":140},{"sl":141},{"sl":149},{"sl":151},{"sl":153},{"sl":156},{"sl":157},{"sl":158},{"sl":168},{"sl":169},{"sl":171},{"sl":178},{"sl":179},{"sl":182},{"sl":183},{"sl":187},{"sl":193},{"sl":196},{"sl":197},{"sl":198},{"sl":202}],"pass":true},"test_0":{"methods":[{"sl":122},{"sl":175},{"sl":190}],"name":"testFilterHashCodeAndEquals","statements":[{"sl":124},{"sl":125},{"sl":178},{"sl":182},{"sl":183},{"sl":187},{"sl":193},{"sl":196},{"sl":197},{"sl":198},{"sl":202}],"pass":true},"test_16":{"methods":[{"sl":90},{"sl":129},{"sl":166},{"sl":175},{"sl":190}],"name":"testServiceInjection","statements":[{"sl":91},{"sl":93},{"sl":94},{"sl":95},{"sl":96},{"sl":100},{"sl":101},{"sl":102},{"sl":131},{"sl":133},{"sl":135},{"sl":138},{"sl":139},{"sl":140},{"sl":141},{"sl":168},{"sl":169},{"sl":171},{"sl":178},{"sl":179},{"sl":182},{"sl":183},{"sl":187},{"sl":193},{"sl":196},{"sl":197},{"sl":198},{"sl":202}],"pass":true},"test_14":{"methods":[{"sl":90},{"sl":129},{"sl":166},{"sl":175},{"sl":190}],"name":"testDirectServiceInjection","statements":[{"sl":91},{"sl":93},{"sl":94},{"sl":95},{"sl":96},{"sl":100},{"sl":101},{"sl":102},{"sl":131},{"sl":133},{"sl":135},{"sl":138},{"sl":139},{"sl":140},{"sl":141},{"sl":168},{"sl":169},{"sl":171},{"sl":178},{"sl":179},{"sl":182},{"sl":183},{"sl":187},{"sl":193},{"sl":202}],"pass":true},"test_22":{"methods":[{"sl":175}],"name":"testServiceTypesAndSignatures","statements":[{"sl":178},{"sl":182},{"sl":183}],"pass":true},"test_6":{"methods":[{"sl":90},{"sl":129},{"sl":175},{"sl":190}],"name":"testDecoratedServiceInjection","statements":[{"sl":91},{"sl":93},{"sl":94},{"sl":95},{"sl":96},{"sl":131},{"sl":133},{"sl":135},{"sl":138},{"sl":139},{"sl":140},{"sl":141},{"sl":178},{"sl":182},{"sl":183},{"sl":187},{"sl":193},{"sl":196},{"sl":197},{"sl":198},{"sl":202}],"pass":true}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [4], [4], [], [4], [], [], [4], [], [], [4], [4], [], [], [4], [4], [], [], [4], [], [], [4], [4], [], [], [4], [4], [], [], [], [], [], [], [], [], [16, 14, 11, 6], [16, 14, 11, 6], [], [16, 14, 11, 6], [16, 14, 11, 6], [16, 14, 11, 6], [16, 14, 11, 6], [11], [11], [11], [16, 14, 11], [16, 14, 11], [16, 14, 11], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [0, 4, 12], [], [0, 4, 12], [0, 4, 12], [], [], [], [16, 4, 14, 11, 6], [], [16, 4, 14, 11, 6], [], [16, 4, 14, 11, 6], [], [16, 4, 14, 11, 6], [], [], [16, 4, 14, 11, 6], [16, 14, 11, 6], [16, 14, 11, 6], [16, 14, 11, 6], [], [], [], [], [], [11], [], [11], [], [11], [], [11], [], [], [11], [11], [11], [], [], [], [], [], [], [], [16, 14, 11], [], [16, 14, 11], [16, 14, 11], [], [16, 14, 11], [], [], [], [16, 0, 4, 12, 14, 11, 6, 22], [], [], [16, 0, 4, 12, 14, 11, 6, 22], [16, 14, 11], [], [], [16, 0, 4, 12, 14, 11, 6, 22], [16, 0, 4, 12, 14, 11, 6, 22], [], [], [], [16, 0, 4, 12, 14, 11, 6], [], [], [16, 0, 4, 12, 14, 11, 6], [], [], [16, 0, 4, 12, 14, 11, 6], [], [], [16, 0, 4, 12, 11, 6], [16, 0, 4, 12, 11, 6], [16, 0, 4, 12, 11, 6], [], [], [], [16, 0, 4, 12, 14, 11, 6], [], []]