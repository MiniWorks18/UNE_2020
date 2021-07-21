options(digits = 3,show.signif.stars = F)

rats <- read.table('ratWeights.txt', header = TRUE)

attach(rats)

rats$Treatment <- factor(rats$Treatment)

#Null hypothesis1: MU_DOC = MU_WKY
#Null hypothesis2: (MU_DOC + MU_WKY)/2 = (MU_DOC-CA + MU_WKY-CA)/2
#Null hypothesis3: Mu_DOC-CA = MU_WKY-CA

#Constrant1: (1,0,-1,0)
#Constrant2: (1,-1,1,-1)
#Constrant3: (0,1,0,-1)

C1<-C(rats$Treatment,c(1,0,-1,0),1)
C2<-C(rats$Treatment,c(1,-1,1,-1),1)
C3<-C(rats$Treatment,c(0,1,0,-1),1)

mod1<-lm(weight~Treatment)

mod2<-lm(weight~C1+C2+C3, data=rats)

plot(mod2)

plot(mod1)
anova(mod1)
