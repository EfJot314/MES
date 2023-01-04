using LinearAlgebra
using Plots


#calkowanie numeryczne - metoda trapezow
function integrate(f, a, b)
    value = 0.0
    N = 10000
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
    return x -> (x >= a && x < b) ? (x-a)/h : 0
end

#funkcja malejaca
function createFunction2(a,b)
    h = b-a
    return x -> (x >= a && x < b) ? (b-x)/h : 0
end

#suma dwoch funkcji f1+f2
function addedFunction(f1, f2)
    return x -> f1(x)+f2(x)
end

#iloczyn dwoch funkcji
function multipliedFunction(f1, f2)
    return x -> f1(x)*f2(x)
end

#dzielenie dwoch funkcji
function dividedFunction(f1, f2)
    return x -> f1(x)/f2(x)
end

#pochodna 
function derivative(f)
    h = 1/100000
    return x->(f(x+h)-f(x))/h
end

#funkcja epsilon podana w zadaniu
function eps(x)
    if x >= 0 && x <= 1
        return 10
    elseif x > 1 && x <= 2
        return 5
    elseif x > 2 && x <= 3
        return 1
    else
        return 0
    end
end

#funkcja rho dana w zadaniu
function rho(x)
    return 1
end

#shift ktory zostal wprowadzony
function shift(x)
    return 2
end


function Bfun(f1,f2)
    poch1 = derivative(f1)
    poch2 = derivative(f2)
    return f1(0)*f2(0) - integrate(multipliedFunction(poch1, poch2), 0, 3)
end


function Lfun(f)
    f1 = multipliedFunction(dividedFunction(rho,eps), f)
    return 5*f(0)-integrate(f1, 0, 3)
end


xp = 0
xk = 3

N = 50
h = (xk-xp)/N


#tworze liste funkcji ksztaltu
funTab = []
for i in 0:N-1
    ind = i+1
    if ind == 1
        #pierwsza wyglada troszke inaczej
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

#macierz A (elementy to wartosci funkcji B(ei,ej))
A = zeros(N, N)
for i in 1:N
    for j in 1:N
        A[i,j] = Bfun(funTab[i], funTab[j])
    end
end

#macierz B (elementy to L(e1)-B(shift,ei))
B = zeros(N, 1)
for i in 1:N
    B[i] = Lfun(funTab[i]) - Bfun(shift, funTab[i])
end

#C to macierz zawierajaca wspolczynniki ui
C = zeros(N,1)
if(det(A) != 0)
    C = inv(A)*B
end


#ostateczne skladanie funkcji
fi = x->shift(x)
for i in 1:N
    # fi = fi+ui*ei
    global fi = addedFunction(fi, multipliedFunction(x->C[i,1], funTab[i]))
end


#tworze wykres funkcji fi
x = range(0, 3, 10000)
pl=plot(x, fi.(x))


