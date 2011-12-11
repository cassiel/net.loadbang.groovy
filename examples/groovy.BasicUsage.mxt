max v2;#N vpatcher 28 66 678 452;#P window setfont Monaco 9.;#P comment 267 77 227 262153 Groovy for Max/MSP;#P comment 267 89 327 262153 Nick Rothwell \, nick@cassiel.com / nick@loadbang.net;#P comment 267 101 253 262153 Download from: http://www.loadbang.net;#P user panel 264 74 335 43;#X brgb 80 110 80;#X frgb 0 0 0;#X border 2;#X rounded 0;#X shadow 0;#X done;#P comment 47 302 190 262153 Thread house-keeping;#N vpatcher 75 411 761 637;#P window setfont Monaco 9.;#P window linecount 1;#P message 108 115 40 262153 clear;#P window linecount 0;#P message 12 115 40 262153 start;#P window linecount 1;#P newex 12 150 382 262153 mxj net.loadbang.groovy.mxj.ScriptEngine @script ThreadExample;#P hidden newex 164 261 124 262153 bgcolor 100 130 100;#P window linecount 3;#P comment 10 41 306 262153 It's important to clean up threads \, socket listeners etc. when the MXJ instance is deleted. ThreadExample adds a cleanup to stop the thread;#P window linecount 4;#P comment 171 94 229 262153 Sending a "clear" (or calling engine.clear() in Groovy) clears the binding environment and also unwinds the cleanup stack;#P window linecount 6;#P comment 418 89 226 262153 Note: reloading the script will NOT clean up any background activity by the old one (although it will add a new cleanup handler) - so you may wish to send in a "clear" first \, which will unwind all handlers;#P user panel 8 86 402 99;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P connect 6 0 5 0;#P fasten 7 0 5 0 113 139 17 139;#P pop;#P newobj 49 319 124 262153 p ThreadsAndCleanup;#P user panel 45 299 200 41;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P comment 47 257 190 262153 Max list ops using classes;#N vpatcher 138 325 788 734;#P button 196 332 15 0;#P button 223 245 15 0;#P window setfont Monaco 9.;#P number 69 332 102 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 309 245 45 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 155 245 45 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P user ubumenu 24 219 100 262153 0 1 1 0;#X add add;#X add multiply;#X add max;#X prefix_set 0 0 <none> 0;#P newex 69 243 76 262153 prepend run;#P window linecount 2;#P newex 69 295 250 262153 mxj net.loadbang.groovy.mxj.ScriptEngine 2 1 @placeholder groovy-place;#P window linecount 3;#P comment 421 82 191 262153 NB: if loading a script via @script \, it is not safe to do output during initialisation;#P window linecount 1;#P hidden newex 72 361 124 262153 bgcolor 100 130 100;#P number 22 121 102 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P message 22 58 31 262153 lap;#P message 92 58 82 262153 script Timer;#P newex 22 90 334 262153 mxj net.loadbang.groovy.mxj.ScriptEngine @script Timer;#P window linecount 0;#P comment 421 42 187 262153 All script files are fetched from Max's search path;#P window linecount 1;#P comment 245 114 145 262153 looks for Timer.groovy;#P window linecount 2;#P comment 183 56 215 262153 Since "script" is an attribute \, we can reload it at any time;#P window linecount 3;#P comment 421 136 204 262153 If you mix @script and @args \, bear in mind that the script may run before the args are bound;#P window linecount 0;#P comment 160 200 264 262153 A ScriptEngine can be created with a place-holder: it can then efficiently (re)load scripts from the same directory on demand;#P comment 232 342 170 262153 When using "run" \, Groovy will only recompile scripts than have changed on disk;#P user panel 18 54 383 85;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P comment 332 270 92 262153 (The place-holder allows script names to be "scoped" locally);#P user panel 20 197 408 186;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P fasten 10 0 9 0 97 84 27 84;#P connect 11 0 9 0;#P connect 9 0 12 0;#P connect 17 1 16 0;#P connect 16 0 15 0;#P fasten 18 0 15 0 160 290 74 290;#P fasten 21 0 15 0 228 290 74 290;#P connect 15 0 20 0;#P fasten 15 0 22 0 74 326 201 326;#P connect 19 0 15 1;#P pop;#P newobj 49 229 88 262153 p ScriptFiles;#P comment 47 212 190 262153 Loading and running scripts;#P user panel 45 209 200 41;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#N vpatcher 278 60 767 305;#P window setfont Monaco 9.;#P message 60 133 40 262153 clear;#P window linecount 1;#P message 383 133 58 262153 getvar Z;#P message 324 133 58 262153 getvar Y;#P button 71 189 15 0;#P number 11 189 58 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 165 83 41 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 88 83 41 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 11 83 41 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P window linecount 0;#P message 265 133 58 262153 getvar X;#P window linecount 1;#P message 360 101 94 262153 exec Z = X * Y;#P window linecount 0;#P message 260 101 94 262153 exec X = Y + Z;#P window linecount 1;#P message 165 103 76 262153 setvar Z \$1;#P message 88 103 76 262153 setvar Y \$1;#P window linecount 0;#P message 11 103 76 262153 setvar X \$1;#P newex 11 165 250 262153 mxj net.loadbang.groovy.mxj.ScriptEngine;#P window linecount 1;#P hidden newex 300 181 124 262153 bgcolor 100 130 100;#P window linecount 0;#P comment 7 37 206 262153 Every Groovy script object has a "binding" containing variables;#P comment 260 85 165 262153 Simple binding assignments;#P comment 102 131 137 262153 clear all (except maxObject and engine);#P user panel 7 79 451 129;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P connect 12 0 6 0;#P connect 6 0 5 0;#P fasten 7 0 5 0 93 125 16 125;#P fasten 8 0 5 0 170 125 16 125;#P fasten 11 0 5 0 270 156 16 156;#P fasten 17 0 5 0 329 156 16 156;#P fasten 18 0 5 0 388 156 16 156;#P fasten 9 0 5 0 265 129 16 129;#P fasten 10 0 5 0 365 129 16 129;#P fasten 19 0 5 0 65 152 16 152;#P connect 5 0 15 0;#P fasten 5 0 16 0 16 185 76 185;#P connect 13 0 7 0;#P connect 14 0 8 0;#P pop;#P newobj 49 184 46 262153 p Vars;#P comment 47 167 190 262153 Setting and getting variables;#P user panel 45 164 200 41;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P comment 47 122 190 262153 Inlets \, outlets \, attributes;#P window setfont Monaco 18.;#P comment 49 32 186 262162 Groovy for MXJ;#P window setfont Monaco 9.;#P hidden newex 303 286 124 262153 bgcolor 100 130 100;#P user fpic 265 122 109 57 groovy-50.png 2 2 0 0. 0 0 0;#N vpatcher 18 199 606 487;#P window setfont Monaco 9.;#P window linecount 1;#P hidden newex 164 261 124 262153 bgcolor 100 130 100;#P user ubumenu 12 79 100 262153 0 1 1 0;#X add append;#X add intersect;#X add reverse;#X add register;#X prefix_set 0 0 <none> 0;#P message 165 124 88 262153 script ListOp;#P newex 314 56 99 262153 pak 0 0 0;#P number 402 34 43 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 358 34 43 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 314 34 43 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P window linecount 0;#P newex 172 56 99 262153 pak 0 0 0;#P number 260 34 43 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 216 34 43 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 172 34 43 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P message 74 218 215 262153 0 0 0;#P newex 74 191 76 262153 prepend set;#P button 74 119 15 0;#P newex 74 164 364 262153 mxj net.loadbang.groovy.mxj.ScriptEngine 2 1 @script ListOp;#P comment 256 120 100 262153 (reload during development);#P comment 340 196 100 262153 "intersect" will irritate Max if the output list is empty!;#P comment 459 32 100 262153 This example shows a simple use of Groovy packages and classes \, to dynamically change a list operation;#P user panel 9 31 439 227;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P fasten 17 1 4 0 62 144 79 144;#P connect 16 0 4 0;#P connect 11 0 4 0;#P connect 5 0 4 0;#P connect 4 0 6 0;#P connect 6 0 7 0;#P connect 8 0 11 0;#P connect 9 0 11 1;#P connect 10 0 11 2;#P connect 12 0 15 0;#P connect 13 0 15 1;#P connect 14 0 15 2;#P connect 15 0 4 1;#P pop;#P newobj 49 274 100 262153 p SimpleClasses;#N vpatcher 161 163 767 714;#P window setfont Monaco 9.;#P newex 22 72 58 262153 loadbang;#P window linecount 1;#P hidden newex 287 482 124 262153 bgcolor 100 130 100;#P number 186 199 35 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 318 127 35 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 54 199 35 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P window linecount 0;#P message 22 94 364 262153 exec _int = { inlet\\\, x -> maxObject.outlet(1-inlet\\\, -x) };#P number 54 127 35 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P window linecount 1;#P newex 54 163 274 262153 mxj net.loadbang.groovy.mxj.ScriptEngine 2 2;#P objectname mxj.MAIN[2];#P window setfont Monaco 18.;#P comment 19 28 282 262162 Attributes and Arguments;#P window setfont Monaco 9.;#P message 92 407 220 262153 eval maxObject.args.collect { -it };#P number 208 504 47 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 160 504 47 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 112 504 47 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 64 504 47 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P number 16 504 47 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P newex 60 474 106 262153 unpack 0 0 0 0 0;#P message 60 386 286 262153 eval maxObject.args.collect { x -> x * x * x };#P newex 60 435 364 262153 mxj net.loadbang.groovy.mxj.ScriptEngine @args 1 3 5 7 9 10;#P objectname mxj.MAIN[4];#P message 33 266 226 262153 exec println(maxObject.args[0] + 15);#P newex 33 296 364 262153 mxj net.loadbang.groovy.mxj.ScriptEngine @args 2 X 49 false;#P objectname mxj.MAIN[1];#P comment 34 236 306 262153 Use the "args" attribute to set up some arguments;#P window linecount 0;#P comment 119 133 175 262153 Specify two ints for number of inlets and outlets;#P comment 406 72 160 262153 With multiple inlets \, Groovy functions are passed the inlet number;#P comment 403 151 158 262153 "_int" is the magic name for the function invoked when an integer is input;#P comment 32 343 249 262153 A bit of Groovy closure magic: map a function over a list of integers as args;#P user panel 18 68 372 149;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P user panel 29 262 373 55;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P user panel 12 382 416 140;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P connect 12 0 13 0;#P connect 27 0 22 0;#P connect 9 0 8 0;#P fasten 22 0 20 0 27 151 59 151;#P connect 21 0 20 0;#P connect 20 0 23 0;#P connect 18 0 10 0;#P connect 11 0 10 0;#P connect 10 0 12 0;#P connect 12 1 14 0;#P connect 12 2 15 0;#P connect 12 3 16 0;#P connect 20 1 25 0;#P connect 12 4 17 0;#P connect 24 0 20 1;#P pop;#P newobj 49 139 154 262153 p AttributesAndArguments;#N vpatcher 99 158 922 554;#P window setfont Monaco 9.;#P window linecount 1;#P hidden newex 614 350 124 262153 bgcolor 100 130 100;#P flonum 455 204 71 9 0 0 4 4 0 0 0 221 221 221 222 222 222 0 0 0;#P message 455 225 58 262153 mySin \$1;#P message 425 159 340 262153 exec mySin = { f -> maxObject.outlet(0\\\, Math.sin(f)) };#P flonum 455 307 80 9 0 0 164 4 0 0 0 221 221 221 222 222 222 0 0 0;#P newex 455 276 250 262153 mxj net.loadbang.groovy.mxj.ScriptEngine;#P objectname mxj.MAIN[5];#P message 30 339 197 262153 3.141593;#P newex 30 315 76 262153 prepend set;#P message 207 264 136 262153 eval Math.asin(1) * 2;#P message 30 264 100 262153 eval new Date();#P newex 30 291 250 262153 mxj net.loadbang.groovy.mxj.ScriptEngine;#P objectname mxj.MAIN[3];#P message 195 146 184 262153 exec println Math.asin(1) * 2;#P message 18 146 148 262153 exec println new Date();#P newex 18 173 250 262153 mxj net.loadbang.groovy.mxj.ScriptEngine;#P objectname mxj.MAIN[2];#P window linecount 4;#P comment 18 85 187 262153 This is a basic Groovy instantiation with no Script file - all we do is print something;#P window linecount 2;#P comment 27 232 183 262153 Alternatively: use "eval" to output to outlet 0;#P window linecount 3;#P comment 429 107 278 262153 Slightly more complex: use "exec" to define a function which outputs a value (we don't have a pure eval for functions - yet);#P window linecount 0;#P comment 556 185 178 262153 We predefine 'maxObject' as a proxy for the enclosing MXJ instance;#P window setfont Monaco 18.;#P comment 24 40 186 262162 Very Basic Usage;#P window setfont Monaco 9.;#P comment 530 239 175 262153 A message with the function name will call the function;#P user panel 14 142 369 52;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P user panel 26 260 321 98;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P user panel 421 155 349 170;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P connect 10 0 9 0;#P fasten 11 0 9 0 200 166 23 166;#P connect 13 0 12 0;#P fasten 14 0 12 0 212 284 35 284;#P connect 12 0 15 0;#P connect 15 0 16 0;#P connect 21 0 20 0;#P connect 20 0 17 0;#P fasten 19 0 17 0 430 261 460 261;#P connect 17 0 18 0;#P pop;#P newobj 49 94 106 262153 p VeryBasicUsage;#P user panel 264 122 110 61;#X brgb 255 255 255;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P comment 47 77 190 262153 The basics: Groovy expressions;#P user panel 45 74 200 41;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P user panel 45 119 200 41;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P user panel 45 254 200 41;#X brgb 120 150 120;#X frgb 0 0 0;#X border 1;#X rounded 0;#X shadow 0;#X done;#P pop;