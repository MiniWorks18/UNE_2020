#include <stdio.h>
#include <stdlib.h>
int main()
{
	const char* s = getenv("PATH");
	printf("PATH: %s\n", (s!=NULL)? s : "getenv returned NULL");
}
