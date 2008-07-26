var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = { "classes" : [
    {"id" : 644, "sl" : 40, "el" : 136, "name" : "ServiceDecorationTests",
    "methods" : [
             {"sl" : 43, "el" : 54, "sc" : 3},  {"sl" : 112, "el" : 123, "sc" : 3},  {"sl" : 125, "el" : 135, "sc" : 3}  ]}
    ,
    {"id" : 648, "sl" : 56, "el" : 70, "name" : "ServiceDecorationTests.BrokenDecorator",
    "methods" : [
             {"sl" : 58, "el" : 69, "sc" : 5},  {"sl" : 61, "el" : 63, "sc" : 9},  {"sl" : 65, "el" : 67, "sc" : 9}  ]}
    ,
    {"id" : 654, "sl" : 72, "el" : 97, "name" : "ServiceDecorationTests.StickyDecorator",
    "methods" : [
             {"sl" : 75, "el" : 96, "sc" : 5},  {"sl" : 80, "el" : 92, "sc" : 9},  {"sl" : 94, "el" : 94, "sc" : 9}  ]}
    ,
    {"id" : 671, "sl" : 99, "el" : 103, "name" : "ServiceDecorationTests.Holder",
    "methods" : [
              ]}
    
 ]
};

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
		"test_9" : {
					  "name" : "stickyService",
					  "pass" : true,
					  "methods" : [{"sl": 80 },{"sl": 94 },{"sl": 125 },],
					  "statements" : [{"sl": 81 },{"sl": 82 },{"sl": 83 },{"sl": 84 },{"sl": 88 },{"sl": 91 },{"sl": 126 },{"sl": 127 },{"sl": 128 },{"sl": 129 },{"sl": 130 },{"sl": 131 },{"sl": 132 },{"sl": 133 },{"sl": 134 },]
					  },
		"test_12" : {
					  "name" : "brokenServices",
					  "pass" : true,
					  "methods" : [{"sl": 58 },{"sl": 61 },{"sl": 112 },],
					  "statements" : [{"sl": 60 },{"sl": 62 },{"sl": 113 },{"sl": 115 },{"sl": 116 },{"sl": 117 },{"sl": 119 },{"sl": 120 },{"sl": 122 },]
					  }
 };

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [  [],   [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 12   ] ,
  [  ] ,
  [ 12   ] ,
  [ 12   ] ,
  [ 12   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 9   ] ,
  [  ] ,
  [  ] ,
  [ 9   ] ,
  [  ] ,
  [  ] ,
  [ 9   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 12   ] ,
  [ 12   ] ,
  [  ] ,
  [ 12   ] ,
  [ 12   ] ,
  [ 12   ] ,
  [  ] ,
  [ 12   ] ,
  [ 12   ] ,
  [  ] ,
  [ 12   ] ,
  [  ] ,
  [  ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [ 9   ] ,
  [  ] ,
  [  ] 
];
