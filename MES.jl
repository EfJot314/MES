using LinearAlgebra
using Plots

#calkowanie numeryczne metoda trapezow
function integrate(f, a, b)
    value = 0.0
    N = 100
    h = (b-a)/N
    for i in 0:N-1
        x = a+i*h
        value += 0.5*h*(f(x)+f(x+h))
    end

    return value
end

#funkcja rosnaca
function createFunction1(a,b)
    h = b-a
    return x -> (x > a && x <= b) ? (x-a)/h : 0
end

#funkcja malejaca
function createFunction2(a,b)
    h = b-a
    return x -> (x > a && x <= b) ? (b-x)/h : 0
end

#suma dwoch funkcji f1+f2
function addedFunction(f1, f2)
    return x -> f1(x)+f2(x)
end



xp = 0
xk = 3

N = 7
h = (xk-xp)/N


#tworze liste funkcji ksztaltu
funTab = []
for i in 0:N-1
    ind = i+1
    if ind == 1
        a = i*h
        b = (i+1)*h
        fun = createFunction2(a,b)
    else
        a = (i-1)*h
        b = i*h
        c = (i+1)*h
        f1 = createFunction1(a,b)
        f2 = createFunction2(b,c)
        fun = addedFunction(f1,f2)
    end
    push!(funTab, fun)
    
end





#tworze wykres
x = range(0, 3, 10000)


plotTab = []
for i in 1:N
    J = funTab[i]
    y = J.(x)
    push!(plotTab, y)
end


plot(x, plotTab)

