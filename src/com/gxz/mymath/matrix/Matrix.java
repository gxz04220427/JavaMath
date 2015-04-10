package com.gxz.mymath.matrix;
/**
 *                            _ooOoo_
 *                           o8888888o
 *                           88" . "88
 *                           (| -_- |)
 *                           O\  =  /O
 *                        ____/`---'\____
 *                      .'  \\|     |//  `.
 *                     /  \\|||  :  |||//  \
 *                    /  _||||| -:- |||||-  \
 *                    |   | \\\  -  /// |   |
 *                    | \_|  ''\---/''  |   |
 *                    \  .-\__  `-`  ___/-. /
 *                  ___`. .'  /--.--\  `. . __
 *               ."" '<  `.___\_<|>_/___.'  >'"".
 *              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *              \  \ `-.   \_ __\ /__ _/   .-` /  /
 *         ======`-.____`-.___\_____/___.-`____.-'======
 *                            `=---='
 *         ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                        佛祖保佑       永无BUG
 */
import com.gxz.mymath.determinant.Determinant;

/**
 * 
 * @类型功能描述：Java矩阵算法类
 * @作者 郭晓忠(guoxiaozhong)
 * @修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class Matrix {

	private double[][] matrixArray;
	private int column;
	private int row;
//	private double module;
//	private boolean mirror;
	private Matrix transposeMatrix;

	/**
	 * 
	 * @构造函数：构造普通矩阵
	 * @说明：
	 * @param w
	 * @param h
	 */
	public Matrix(int r, int c) {
		this.row = r;
		this.column = c;
		matrixArray = new double[r][c];
	}

	/**
	 * 
	 * @构造函数：构造方阵
	 * @说明：
	 * @param n
	 */
	public Matrix(int n) {
		this.column = n;
		this.row = n;
		matrixArray = new double[n][n];
	}

	/**
	 * 
	 * @构造函数：复制矩阵
	 * @说明：
	 * @param matrix
	 */
	public Matrix(Matrix matrix) {
		this.column = matrix.column;
		this.row = matrix.row;
		this.matrixArray = new double[this.column][this.row];
	}

	/**
	 * 
	 * @方法功能描述：设置矩阵元素的值
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月8日 下午8:45:18</p>
	 * @param w
	 * @param h
	 * @param value
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void set(int w, int h, double value) {
		this.matrixArray[w][h] = value;
	}

	/**
	 * 
	 * @方法功能描述：获取矩阵元素的值
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月8日 下午8:45:36</p>
	 * @param w
	 * @param h
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public double get(int w, int h) {
		return this.matrixArray[w][h];
	}

	/**
	 * 
	 * @方法功能描述：设为单位阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月8日 下午8:50:43</p>
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void SetUnit() {
		for (int i = 0; i < this.column; i++) {
			for (int j = 0; j < this.row; j++) {
				if (i == j) {
					this.matrixArray[i][j] = 1;
				} else {
					this.matrixArray[i][j] = 0;
				}
			}
		}
	}

	/**
	 * 
	 * @方法功能描述：调换矩阵两行
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月8日 下午9:11:07</p>
	 * @param row1
	 * @param row2
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void RowExchange(int row1, int row2) throws Exception {
		double tempdouble;
		if (row1 >= this.row || row2 >= this.row) {
			throw new Exception("给定索引超出范围");
		} else {
			for (int i = 0; i < this.column; i++) {
				tempdouble = this.matrixArray[row1][i];
				this.matrixArray[row1][i] = this.matrixArray[row2][i];
				this.matrixArray[row2][i] = tempdouble;
			}
		}
	}

	/**
	 * 
	 * @方法功能描述：矩阵初等变换，将第row行乘以mul
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月8日 下午9:14:13</p>
	 * @param row
	 * @param mul
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void RowMultiple(int row, double mul) {
		for (int i = 0; i < this.column; i++) {
			this.matrixArray[row][i] *= mul;
		}
	}

	/**
	 * 
	 * @方法功能描述：矩阵初等变换，将第row2行数据乘以mul加到第row1行
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月8日 下午9:17:00</p>
	 * @param row1
	 * @param row2
	 * @param mul
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void RowMultipleAdd(int row1, int row2, double mul) {
		for (int i = 0; i < this.column; i++) {
			this.matrixArray[row1][i] += (this.matrixArray[row2][i] * mul);
		}
	}

	/**
	 * 
	 * @方法功能描述：求转置矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月8日 下午9:22:42</p>
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Matrix getTransposeMatrix() {
		this.transposeMatrix = new Matrix(this.column, this.row);
		for (int i = 0; i < this.column; i++) {
			for (int j = 0; j < this.row; j++) {
				this.transposeMatrix.set(i, j, this.get(j, i));
			}
		}
		return transposeMatrix;
	}

	/**
	 * 
	 * @方法功能描述：矩阵相加
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月8日 下午9:42:11</p>
	 * @param m1
	 * @param m2
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static Matrix AddMatrix(Matrix m1, Matrix m2) throws Exception {
		Matrix resultMatrix;
		if (m1.column != m2.column || m1.row != m2.row) {
			throw new Exception("两个矩阵大小不一致，不能相加");
		} else {
			resultMatrix = new Matrix(m1.column, m1.row);
			for (int i = 0; i < m1.column; i++) {
				for (int j = 0; j < m1.row; j++) {
					resultMatrix.set(i, j, m1.get(i, j) + m2.get(i, j));
				}
			}
		}
		return resultMatrix;
	}

	/**
	 * 
	 * @方法功能描述：矩阵相减
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月8日 下午9:55:27</p>
	 * @param m1
	 * @param m2
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static Matrix MinusMatrix(Matrix m1, Matrix m2) throws Exception {
		Matrix resultMatrix;
		if (m1.column != m2.column || m1.row != m2.row) {
			throw new Exception("两个矩阵大小不一致，不能相加");
		} else {
			resultMatrix = new Matrix(m1.column, m1.row);
			for (int i = 0; i < m1.column; i++) {
				for (int j = 0; j < m1.row; j++) {
					resultMatrix.set(i, j, m1.get(i, j) - m2.get(i, j));
				}
			}
		}
		return resultMatrix;

	}

	/**
	 * 
	 * @方法功能描述：矩阵乘法
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 上午9:29:39</p>
	 * @param m1
	 * @param m2
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static Matrix multipleMatrix(Matrix m1, Matrix m2) throws Exception {
		if (m1.column != m2.row) {
			throw new Exception("矩阵对应关系不正确,不能相乘");
		}
		Matrix resultmMatrix = new Matrix(m1.column, m2.row);
		for (int i = 0; i < m1.row; i++) {
			for (int j = 0; j < m2.column; j++) {
				for (int k = 0; k < m1.column; k++) {
					resultmMatrix.set(
							i,
							j,
							resultmMatrix.get(i, j)
									+ (m1.get(i, k) * m2.get(k, j)));
				}

			}
		}
		return resultmMatrix;
	}

	/**
	 * 
	 * @方法功能描述：常数乘以矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 上午9:37:56</p>
	 * @param mul
	 * @param matrix
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static Matrix multipleMatrix(double mul, Matrix matrix) {
		Matrix resultMatrix = new Matrix(matrix.row, matrix.column);
		for (int i = 0; i < matrix.row; i++) {
			for (int j = 0; j < matrix.column; j++) {
				resultMatrix.set(i, j, matrix.get(i, j) * mul);
			}
		}
		return resultMatrix;
	}

	/**
	 * 
	 * @方法功能描述：查找矩阵的列主元
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 上午9:47:44</p>
	 * @param startrow
	 * @param searchcolumn
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public int ColumnPivot(int startrow, int searchcolumn) {
		int indextemp = startrow;
		double datatemp = this.get(startrow, searchcolumn);
		for (int i = startrow; i < this.row; i++) {
			if (this.get(i, searchcolumn) > datatemp) {
				datatemp = this.get(i, searchcolumn);
				indextemp = i;
			}
		}
		return indextemp;
	}

	/**
	 * 
	 * @方法功能描述：计算矩阵的模
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 上午10:29:27</p>
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public double getModule() throws Exception {
		return new Determinant(this).getDet();
	}

	/**
	 * 
	 * @方法功能描述：获取矩阵的代数余子式矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 上午10:35:34</p>
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Matrix getAlgebraicComplementMinorMatrix() throws Exception {
		Matrix resultmMatrix = new Matrix(this.row, this.column);
		Determinant determinant = new Determinant(this);
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				resultmMatrix.set(i, j,
						determinant.getAlgebraicComplementMinor(i, j).getDet()
								* Math.pow(-1, i + j));
			}
		}
		return resultmMatrix;
	}

	/**
	 * 
	 * @方法功能描述：计算矩阵的逆
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 上午10:37:31</p>
	 * @return
	 * @throws Exception
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public Matrix getInverse() throws Exception {
		return multipleMatrix(1 / getModule(),
				getAlgebraicComplementMinorMatrix());
	}

	/**
	 * 
	 * @方法功能描述：判断是否是对称矩阵
	 * @作者 郭晓忠(guoxiaozhong)
	 * @创建日期 ：2015年4月9日 上午10:41:51</p>
	 * @return
	 * @修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public boolean isMirror() {
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < i; j++) {
				if (this.get(i, j) != this.get(j, i)) {
					return false;
				}
			}
		}
		return true;
	}

	public int getcolumn() {
		return column;
	}

	public int getrow() {
		return row;
	}

	/**
	 * 
	 * @覆盖方法 
	 * @描述
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String resultsString = "";
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.column; j++) {
				resultsString += (String.valueOf(this.get(i, j)) + "\t");
			}
			resultsString += "\n";
		}
		return resultsString;
	}

}
