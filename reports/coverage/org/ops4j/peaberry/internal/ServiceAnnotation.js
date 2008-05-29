var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = { "classes" : [
    {"id" : 226, "sl" : 31, "el" : 144, "name" : "ServiceAnnotation",
    "methods" : [
             {"sl" : 39, "el" : 46, "sc" : 3},  {"sl" : 48, "el" : 50, "sc" : 3},  {"sl" : 52, "el" : 54, "sc" : 3},  {"sl" : 56, "el" : 58, "sc" : 3},  {"sl" : 60, "el" : 62, "sc" : 3},  {"sl" : 64, "el" : 66, "sc" : 3},  {"sl" : 68, "el" : 74, "sc" : 3},  {"sl" : 76, "el" : 88, "sc" : 3},  {"sl" : 94, "el" : 98, "sc" : 3}  ]}
    ,
    {"id" : 252, "sl" : 103, "el" : 143, "name" : "ServiceAnnotation.SecondsAnnotation",
    "methods" : [
              {"sl" : 108, "el" : 110, "sc" : 5},  {"sl" : 112, "el" : 114, "sc" : 5},  {"sl" : 116, "el" : 118, "sc" : 5},  {"sl" : 120, "el" : 123, "sc" : 5},  {"sl" : 125, "el" : 134, "sc" : 5},  {"sl" : 139, "el" : 142, "sc" : 5}  ]}
    
 ]
};

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
		"test_3" : {
					  "name" : "checkAnnotationTypes",
					  "pass" : true,
					  "methods" : [{"sl": 39 },{"sl": 60 },{"sl": 64 },{"sl": 108 },{"sl": 116 },],
					  "statements" : [{"sl": 42 },{"sl": 43 },{"sl": 44 },{"sl": 45 },{"sl": 61 },{"sl": 65 },{"sl": 109 },{"sl": 117 },]
					  },
		"test_5" : {
					  "name" : "checkNotEquals",
					  "pass" : true,
					  "methods" : [{"sl": 39 },{"sl": 60 },{"sl": 76 },{"sl": 108 },{"sl": 125 },],
					  "statements" : [{"sl": 42 },{"sl": 43 },{"sl": 44 },{"sl": 45 },{"sl": 61 },{"sl": 78 },{"sl": 79 },{"sl": 109 },{"sl": 127 },{"sl": 128 },]
					  },
		"test_11" : {
					  "name" : "serviceFilters",
					  "pass" : true,
					  "methods" : [{"sl": 39 },{"sl": 52 },{"sl": 56 },{"sl": 108 },],
					  "statements" : [{"sl": 42 },{"sl": 43 },{"sl": 44 },{"sl": 45 },{"sl": 53 },{"sl": 57 },{"sl": 109 },]
					  },
		"test_14" : {
					  "name" : "testAnnotationConverter",
					  "pass" : true,
					  "methods" : [{"sl": 39 },{"sl": 48 },{"sl": 94 },{"sl": 108 },{"sl": 139 },],
					  "statements" : [{"sl": 42 },{"sl": 43 },{"sl": 44 },{"sl": 45 },{"sl": 49 },{"sl": 96 },{"sl": 109 },{"sl": 141 },]
					  },
		"test_10" : {
					  "name" : "checkServiceAnnotation",
					  "pass" : true,
					  "methods" : [{"sl": 39 },{"sl": 48 },{"sl": 52 },{"sl": 56 },{"sl": 60 },{"sl": 64 },{"sl": 68 },{"sl": 76 },{"sl": 108 },{"sl": 112 },{"sl": 120 },{"sl": 125 },],
					  "statements" : [{"sl": 42 },{"sl": 43 },{"sl": 44 },{"sl": 45 },{"sl": 49 },{"sl": 53 },{"sl": 57 },{"sl": 61 },{"sl": 65 },{"sl": 70 },{"sl": 78 },{"sl": 82 },{"sl": 84 },{"sl": 109 },{"sl": 113 },{"sl": 122 },{"sl": 127 },{"sl": 131 },{"sl": 133 },]
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
  [ 3 , 14 , 11 , 5 , 10   ] ,
  [  ] ,
  [  ] ,
  [ 3 , 14 , 11 , 5 , 10   ] ,
  [ 3 , 14 , 11 , 5 , 10   ] ,
  [ 3 , 14 , 11 , 5 , 10   ] ,
  [ 3 , 14 , 11 , 5 , 10   ] ,
  [  ] ,
  [  ] ,
  [ 14 , 10   ] ,
  [ 14 , 10   ] ,
  [  ] ,
  [  ] ,
  [ 11 , 10   ] ,
  [ 11 , 10   ] ,
  [  ] ,
  [  ] ,
  [ 11 , 10   ] ,
  [ 11 , 10   ] ,
  [  ] ,
  [  ] ,
  [ 3 , 5 , 10   ] ,
  [ 3 , 5 , 10   ] ,
  [  ] ,
  [  ] ,
  [ 3 , 10   ] ,
  [ 3 , 10   ] ,
  [  ] ,
  [  ] ,
  [ 10   ] ,
  [  ] ,
  [ 10   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 5 , 10   ] ,
  [  ] ,
  [ 5 , 10   ] ,
  [ 5   ] ,
  [  ] ,
  [  ] ,
  [ 10   ] ,
  [  ] ,
  [ 10   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 14   ] ,
  [  ] ,
  [ 14   ] ,
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
  [ 3 , 14 , 11 , 5 , 10   ] ,
  [ 3 , 14 , 11 , 5 , 10   ] ,
  [  ] ,
  [  ] ,
  [ 10   ] ,
  [ 10   ] ,
  [  ] ,
  [  ] ,
  [ 3   ] ,
  [ 3   ] ,
  [  ] ,
  [  ] ,
  [ 10   ] ,
  [  ] ,
  [ 10   ] ,
  [  ] ,
  [  ] ,
  [ 5 , 10   ] ,
  [  ] ,
  [ 5 , 10   ] ,
  [ 5   ] ,
  [  ] ,
  [  ] ,
  [ 10   ] ,
  [  ] ,
  [ 10   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 14   ] ,
  [  ] ,
  [ 14   ] ,
  [  ] ,
  [  ] ,
  [  ] 
];
