options(digits=6, show.signif.stars=F)
library(lattice)
source("Rfunctions.R")
samara.df <- read.table("samara.txt",header=T)

samaraEDA <- xyplot(Velocity ~ Load|Tree,data=samara.df,layout=c(3,1),
                    panel=function(x,y){
                      panel.xyplot(x,y)
                      panel.lmline(x,y)} )
print(samaraEDA)

