CFLAGS = -Wall -fpic -coverage -lm -std=c99

all: testme run

testme: testme.c
	gcc -o testme -g testme.c $(CFLAGS)

run:
	./testme

clean:
	rm -rf testme *.gcda *.gcno
