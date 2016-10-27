function [x,dx] = jacobi(A,b,x,eps,N)
%
%  The function jacobi applies Jacobi's method to solve A*x = b.
%  On entry:
%    A       coefficient matrix of the linear system;
%    b       right-hand side vector of the linear system;
%    x       initial approximation;
%    eps     accuracy requirement: stop when norm(dx) < eps;
%    N       maximal number of steps allowed.
%  On return:
%    x       approximation for the solution of A*x = b;
%    dx      vector last used to update x,
%            if success, then norm(dx) < eps.
%
%  Example:
%    Q = orth(rand(5)); D = diag(rand(1,5));
%    A = Q*D*Q'; z = rand(5,1); b = A*z;
%    x = z + rand(5,1)*1e-4;
%    [x,dx] = jacobi(A,b,x,1e-8,50)
%
n = size(A,1);
fprintf('Running the method of Jacobi...\n');
for i = 1:N
   dx = b - A*x;
   for j = 1:n
      dx(j) = dx(j)/A(j,j);
      x(j) = x(j) + dx(j);
   end;
   fprintf('  norm(dx) = %.4e\n', norm(dx));
   if (norm(dx) < eps)
      fprintf('Succeeded in %d steps\n', i);
      return;
   end;
end;
fprintf('Failed to reached accuracy requirement in %d steps.\n', N);
